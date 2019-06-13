import pygame
from pygame.sprite import Sprite

class Alien(Sprite):
	#单个外星人类

	def __init__(self,ai_settings,screen):
		super(Alien,self).__init__()
		self.screen = screen
		self.ai_settings=ai_settings
		#加载外星人图像获取外接矩形
		self.image = pygame.image.load('images/alien.bmp')
		self.rect = self.image.get_rect()
		#将外星人至左上角
		self.rect.x = self.rect.width
		self.rect.y = self.rect.height
		#外星人位置转为浮点数
		self.x=float(self.rect.x)

	def check_edges(self):
		screen_rect=self.screen.get_rect()
		if self.rect.right >= screen_rect.right:
			return True
		if self.rect.left <= 0:
			return True

	def update(self):
		self.x +=self.ai_settings.alien_speed_factor*self.ai_settings.fleet_direction
		self.rect.x=self.x

		#移动标志
	#	self.moving_right=False
	#	self.moving_left=False
	#def update(self):
	#	if self.moving_right and self.rect.right < self.screen_rect.right:
	#		self.center +=self.ai_settings.ship_speed_factor
	#	if self.moving_left and self.rect.left> 0:
	#		self.center -=self.ai_settings.ship_speed_factor
		#整数化飞船位置	
	#	self.rect.centerx = self.center	
	def blitme(self):
		#在制定地方绘制飞船
		self.screen.blit(self.image,self.rect)