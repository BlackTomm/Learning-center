import sys
import pygame
from bullet import Bullet
from alien import Alien
from time import sleep

#根据按键进行响应
def check_keydown_events(event,ai_settings,screen,ship,bullets):
	if event.key==pygame.K_RIGHT:
		ship.moving_right=True
	elif event.key==pygame.K_LEFT:
 		ship.moving_left=True
	elif event.key==pygame.K_SPACE:
 		#创建并控制子弹数量
 		if len(bullets) < ai_settings.bullets_allowed:
 			new_bullet =Bullet(ai_settings,screen,ship)
 			bullets.add(new_bullet)
	elif event.key==pygame.K_q:
 		sys.exit()

def check_keyup_events(event,ship):
 	if event.key==pygame.K_RIGHT:
 		ship.moving_right=False
 	elif event.key==pygame.K_LEFT:
 		ship.moving_left=False

def check_events(ai_settings,screen,stats,sb,play_button,ship,aliens,bullets):
 	#响应按键事件
 	for event in pygame.event.get():
 		if event.type==pygame.QUIT:
 			sys.exit()
 		elif event.type==pygame.KEYDOWN:
 			check_keydown_events(event,ai_settings,screen,ship,bullets)
 				#向右移动
 				#ship.rect.centerx t,+=1
 		elif event.type==pygame.KEYUP:
 			check_keyup_events(event,ship)
 		#检验是否按下play
 		elif event.type == pygame.MOUSEBUTTONDOWN:
 			mouse_x,mouse_y=pygame.mouse.get_pos()
 			check_play_button(ai_settings,screen,stats,sb,play_button,ship,aliens,bullets,mouse_x,mouse_y)

def check_play_button(ai_settings,screen,stats,sb,play_button,ship,aliens,bullets,mouse_x,mouse_y):
	button_clicked=play_button.rect.collidepoint(mouse_x,mouse_y)
	if button_clicked and not stats.game_active:
		ai_settings.initialize_dynamic_settings()
		#隐藏光标
		pygame.mouse.set_visible(False)
		stats.reset_stats()
		stats.game_active=True#顺序不可颠倒
		#重置积分
		sb.prep_score()
		sb.prep_high_score
		sb.prep_level()
		sb.prep_ships()
		#清空并创建所有信息
		aliens.empty()
		bullets.empty()
		create_fleet(ai_settings,screen,ship,aliens)
		ship.center_ship()

def update_screen(ai_settings,screen,stats,sb,ship,aliens,bullets,play_button):
	#重绘屏幕
	screen.fill(ai_settings.bg_color)
	#重绘子弹
	for bullet in bullets.sprites():
		bullet.draw_bullet()
	ship.blitme()
	aliens.draw(screen)
	sb.show_score()
	#开始是调出play按钮
	if not stats.game_active:
		play_button.draw_button()

	pygame.display.flip()#重绘屏幕

def update_bullets(ai_settings,screen,stats,sb,ship,aliens,bullets):
	bullets.update()
	#删除方框外子弹
	for bullet in bullets.copy():
		if bullet.rect.bottom <=0:
			bullets.remove(bullet)
		#print(len(bullets))
	check_alien_bullets_collisions(ai_settings,screen,stats,sb,ship,aliens,bullets)

def check_alien_bullets_collisions(ai_settings,screen,stats,sb,ship,aliens,bullets):
	collisions = pygame.sprite.groupcollide(bullets,aliens,True,True)
	# 判断碰撞计分
	if collisions:
		for aliens in collisions.values():
			stats.score+=ai_settings.alien_points*len(aliens)
			sb.prep_score()
		check_high_score(stats,sb)

	#判断alien是否全部消失并补充
	if len(aliens)==0:
		bullets.empty()
		ai_settings.increase_speed()
		#提高等级
		stats.level+=1
		sb.prep_level()
		create_fleet(ai_settings,screen,ship,aliens)

def get_number_aliens_x(ai_settings,alien_width):
	#获取单行alien数目、第二个参数等效用法
	available_space_x=ai_settings.screen_width-2*alien_width
	number_aliens_x=int(available_space_x/(2*alien_width))
	return number_aliens_x

def get_number_rows(ai_settings,ship_height,alien_height):
	#判定大概使用行数
	available_space_y=ai_settings.screen_height-2*alien_height-ship_height
	number_rows=int(available_space_y/(3*alien_height))
	return number_rows

def create_alien(ai_settings,screen,aliens,alien_number,row_number):
	alien=Alien(ai_settings,screen)
	alien_width=alien.rect.width
	alien.x=alien_width+2*alien_width*alien_number
	alien.rect.x=alien.x
	alien.rect.y=alien.rect.height+2*row_number*alien.rect.height
	aliens.add(alien)


def create_fleet(ai_settings,screen,ship,aliens):
	#计算单行alien数
	alien=Alien(ai_settings,screen)
	number_aliens_x=get_number_aliens_x(ai_settings,alien.rect.width)
	number_rows=get_number_rows(ai_settings,ship.rect.height,alien.rect.height)
	#创建多行
	for row_number in range(number_rows):
		for alien_number in range(number_aliens_x):
			create_alien(ai_settings,screen,aliens,alien_number,row_number)
		
def check_fleet_edges(ai_settings,aliens):
	for alien in aliens.sprites():
		if alien.check_edges():
			change_fleet_direction(ai_settings,aliens)
			break

def change_fleet_direction(ai_settings,aliens):
	for alien in aliens.sprites():
		alien.rect.y +=ai_settings.fleet_drop_speed
	ai_settings.fleet_direction *=-1

def ship_hit(ai_settings,screen,stats,sb,ship,aliens,bullets):
	if stats.ship_left > 0:
		stats.ship_left -=1
		#失败一次清空一次
		aliens.empty()
		bullets.empty()
		#ai_settings.increase_speed()
		sb.prep_ships()
		#在创建新的alien与ship
		create_fleet(ai_settings,screen,ship,aliens)
		ship.center_ship()
		#暂停
		sleep(2)
	else:
		stats.game_active=False
		pygame.mouse.set_visible(True)

def check_aliens_bottom(ai_settings,screen,stats,sb,ship,aliens,bullets):
	screen_rect=screen.get_rect()
	for alien in aliens:
		if alien.rect.bottom >= screen_rect.bottom:
			ship_hit(ai_settings,screen,stats,sb,ship,aliens,bullets)
			break

def update_aliens(ai_settings,screen,stats,sb,ship,aliens,bullets):
	check_fleet_edges(ai_settings,aliens)
	aliens.update()
	#检测aliens与ship是否碰撞
	if pygame.sprite.spritecollideany(ship,aliens):
		ship_hit(ai_settings,screen,stats,sb,ship,aliens,bullets)
	check_aliens_bottom(ai_settings,screen,stats,sb,ship,aliens,bullets)

def check_high_score(stats,sb):
	if stats.score >stats.high_score:
		stats.high_score=stats.score
		sb.prep_high_score()