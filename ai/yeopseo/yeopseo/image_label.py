#!/usr/bin/env python
# coding: utf-8

# In[15]:


import numpy as np
from PIL import Image
import os
import glob
import sys
import numpy as np
import time

#input 이미지 열고 사이즈 확인( 스케치 사진)
# def labeling() :
#     #스케치 이미지 경로
#     print(sketch_img)
#     print(type(sketch_img))
#     while 1:
#         try:
#             sketch_img = glob.glob('image/sketch_img/*.png')
#             image = Image.open(sketch_img[0]) 
#             break
#         except:
#             time.sleep(1)
#             print("아직안됨")
#             continue
    # print('zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz')
    # print(image.size)

sketch_img = glob.glob('image/sketch_img/*.png')
image = Image.open(sketch_img[0]) 

x_size = image.size[0]
y_size = image.size[1]

pix = np.array(image)  

#라벨링 이미지 만들기

class_num = [94, 96, 105, 110, 112, 118, 119, 123, 124, 125, 
            126, 127, 134, 135, 139, 143, 147, 148, 149, 150, 
            153, 154, 156, 158, 161, 162, 168, 170, 174, 176, 
            177, 181]

new_img = np.zeros((y_size,x_size))

#2차원 
for y in range(y_size):
    for x in range(x_size):
        
        if(pix[y][x][0] in class_num) :
            new_img[y][x] = pix[y][x][0]
        else :
            new_img[y][x] = 156  #튀는 값들은 전부 하늘로 

new_img = Image.fromarray(new_img) # NumPy array to PIL image
#new_img.show()

label_img = new_img.convert('L')
#label_img.show()

#라벨링 이미지 저장 경로
label_img.save('image/segmentation_img/val_inst/0001.png')
label_img.save('image/segmentation_img/val_label/0001.png')


# %%
