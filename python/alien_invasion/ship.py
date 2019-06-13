import pygame
from pygame.sprite import Sprite

class Ship(Sprite):
	#initiate the position of the alien
	def __init__(self,ai_settings,screen):
		#初始化飞船
		super(Ship,self).__init__()
		self.screen = screen
		self.ai_settings=ai_settings

		#加载飞船图像获取外接矩形
		self.image = pygame.image.load('images/ship.bmp')
		self.rect = self.image.get_rect()
		self.screen_rect = screen.get_rect()

		#将飞船至于屏幕中央
		self.rect.centerx = self.screen_rect.centerx
		self.rect.bottom = self.screen_rect.bottom

		#飞船位置转为浮点数
		self.center=float(self.rect.centerx)
		#移动标志
		self.moving_right=False
		self.moving_left=False
	def update(self):
		if self.moving_right and self.rect.right < self.screen_rect.right:
			self.center +=self.ai_settings.ship_speed_factor
		if self.moving_left and self.rect.left> 0:
			self.center -=self.ai_settings.ship_speed_factor
		#整数化飞船位置	
		self.rect.centerx = self.center
	def blitme(self):
		#在制定地方绘制飞船
		self.screen.blit(self.image,self.rect)

	def center_ship(self):
		#游戏失败重新调整于中间
		self.center=self.screen_rect.centerx