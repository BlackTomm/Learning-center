import pygame.font
from pygame.sprite import Group
from ship import Ship

class Scoreboard():
	def __init__(self,ai_settings,screen,stats):
		self.screen=screen
		self.screen_rect=screen.get_rect()
		self.ai_settings=ai_settings
		self.stats=stats
		#得分文板
		self.text_color=(30,30,30)
		self.font=pygame.font.SysFont(None,38)
		self.prep_score()
		self.prep_high_score()
		self.prep_level()
		self.prep_ships()

	def prep_score(self):
		#得分转换为图像
		round_score=round(self.stats.score,-1)
		score_str="{:,}".format(round_score)
		self.score_image=self.font.render(score_str,True,self.text_color,self.ai_settings.bg_color)

		#置于左上角
		self.score_rect=self.score_image.get_rect()
		self.score_rect.right=self.screen_rect.right-20
		self.score_rect.top=20

	def prep_high_score(self):
		high_score=round(self.stats.high_score,-1)
		high_score_str="{:,}".format(high_score)
		self.high_score_image=self.font.render(high_score_str,True,self.text_color,self.ai_settings.bg_color)

		#置于中间
		self.high_score_rect=self.score_image.get_rect()
		self.high_score_rect.centerx=self.screen_rect.centerx
		self.high_score_rect.top=20

	def prep_level(self):
		self.level_image=self.font.render(str(self.stats.level),True,self.text_color,self.ai_settings.bg_color)
		#置于右上角
		self.level_rect=self.level_image.get_rect()
		self.level_rect.right=self.screen_rect.right-20
		self.level_rect.top=self.level_rect.bottom+30

	def prep_ships(self):
		#显示剩余飞船
		self.ships=Group()
		for ship_number in range(self.stats.ship_left):
			ship=Ship(self.ai_settings,self.screen)
			ship.rect.x=10+ship_number*ship.rect.width
			ship.rect.y=10
			self.ships.add(ship)

	def show_score(self):
		self.screen.blit(self.score_image,self.score_rect)
		self.screen.blit(self.high_score_image,self.high_score_rect)
		self.screen.blit(self.level_image,self.level_rect)
		self.ships.draw(self.screen)