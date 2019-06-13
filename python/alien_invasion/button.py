import pygame.font

class Button():

	def __init__(self,ai_seetings,screen,msg):
		self.screen=screen
		self.screen_rect=screen.get_rect()
		#设置按钮尺寸
		self.width,self.height=80,50
		self.button_color=(0,225,0)
		self.text_color=(255,255,255)
		self.font=pygame.font.SysFont(None,35)
		#按钮居中
		self.rect=pygame.Rect(0,0,self.width,self.height)
		self.rect.center=self.screen_rect.center
		#按钮创建
		self.prep_msg(msg)

	def prep_msg(self,msg):
			#渲染msg，使按钮显示
			self.msg_image=self.font.render(msg,True,self.text_color,self.button_color)
			self.msg_image_rect=self.msg_image.get_rect()
			self.msg_image_rect.center=self.rect.center

	def draw_button(self):
	#屏幕上显示按钮并绘制文本
		self.screen.fill(self.button_color,self.rect)
		self.screen.blit(self.msg_image,self.msg_image_rect)

