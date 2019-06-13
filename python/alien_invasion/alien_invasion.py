import pygame
from settings import Settings
from ship import Ship
import game_functions as gf
from pygame.sprite import Group
from alien import Alien
from game_stats import Gamestats
from button import Button
from scoreboard import Scoreboard

def run_game():
	#初始化
	pygame.init()  
	ai_settings = Settings()
	screen = pygame.display.set_mode((ai_settings.screen_width,ai_settings.screen_height))
	pygame.display.set_caption('Alien Ivasion')
	#创建play按钮
	play_button=Button(ai_settings,screen,"Play")
	#创建ship及alien
	ship = Ship(ai_settings,screen)
	alien=Alien(ai_settings,screen)

	#创建存储子弹数据
	bullets=Group()
	aliens=Group()
	#储存游戏次数
	stats = Gamestats(ai_settings)
	sb=Scoreboard(ai_settings,screen,stats)
	#创建aliens群
	gf.create_fleet(ai_settings,screen,ship,aliens)

	#游戏主循环
	while True:
		#监听鼠标与键盘
		gf.check_events(ai_settings,screen,stats,sb,play_button,ship,aliens,bullets)
		if stats.game_active:
			ship.update()
			gf.update_bullets(ai_settings,screen,stats,sb,ship,aliens,bullets)
			gf.update_aliens(ai_settings,screen,stats,sb,ship,aliens,bullets)
		gf.update_screen(ai_settings,screen,stats,sb,ship,aliens,bullets,play_button)

run_game()			
