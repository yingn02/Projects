#활용 모듈
import turtle
import random

#리스트
art = ["별", "하트", "구름"]
color = ["red", "orange", "yellow", "green", "blue", "darkblue", "purple",
         "pink", "gold", "khaki", "yellowgreen", "skyblue", "darkgray", "violet"]
x = [] #x좌표
y = [] #y좌표
r = [] #반지름 길이
v = [] #꼭짓점 개수

#반복문
for i in range(-1000,1001,1):
    x.append(i)
for j in range(-600, 601, 1):
    y.append(j)
for k in range(1, 201, 1):
    r.append(k)
for l in range(3, 16, 1):
    v.append(l)

#변수
t1 = turtle.Turtle() #이미지
t2 = turtle.Turtle() #붓
t2.width(4)
t2.speed(0)
s = turtle.Screen() #스크린
s.title("Art Bot")

#이미지 활용
img = "main.gif"
s.addshape(img)

t1.st()
t1.penup()
t1.goto(0, 250)
t1.shape(img)

while True: 
    select = turtle.textinput("Art Bot", #안내 메시지
    " ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n"
    "ㅣ[아트 봇]                                                                                         ㅣ\n"
    "ㅣ랜덤으로 도형을 생성하여 그림을 그려드립니다.                                    ㅣ\n"
    "ㅣ                                                                                                     ㅣ\n"
    "ㅣ[명령어] (대소문자 상관없음)                                                              ㅣ\n"
    "ㅣS - 아트 봇을 실행합니다.                                                                  ㅣ\n"
    "ㅣQ - 프로그램을 종료합니다.                                                                ㅣ\n"
    "ㅣ                                                                                                     ㅣ\n"
    "ㅣ※잘못된 명령어를 입력하면 오류가 발생하오니 주의해주십시오.               ㅣ\n"
    " ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ")

    #조건문, 연산자
    if (select == "S" or select == "s"): #그리기 시작
        t2.clear()
        t1.shape("classic")
        t1.goto(0, 600)
        t2.shape("circle")


        select_bg = random.choice(color)
        s.bgcolor(select_bg)

        for l in range(1, 41, 1): #도형 40개 그리기
            t2.penup()
            t2.left(50)

            select_x = random.choice(x) #좌표 정하기
            select_y = random.choice(y)
            t2.goto(select_x, select_y)

            select_r = random.choice(r) #도형 크기 정하기
            select_v = random.choice(v)

            t2.down()

            select_pc = random.choice(color) #색 정하기
            t2.color(select_pc)
            select_fc = random.choice(color)
            t2.fillcolor(select_fc)

            t2.begin_fill()
            t2.circle(select_r, 360, select_v) #그리기
            t2.end_fill()

        for _ in range(1, 11, 1): #그림 10개 그리기
            t2.penup()
            t2.left(50)

            select_x = random.choice(x) #좌표 정하기
            select_y = random.choice(y)
            t2.goto(select_x, select_y)
            
            select_art = random.choice(art) #그림 모양 정하기

            t2.down()
            
            select_pc = random.choice(color) #색 정하기
            t2.color(select_pc)
            select_fc = random.choice(color)
            t2.fillcolor(select_fc)
            t2.begin_fill()
            
            if (select_art == "별"): #별
                for _ in range(1, 6, 1):
                    t2.forward(150)
                    t2.left(144)

            elif (select_art == "하트"): #하트
                t2.left(45)
                t2.forward(195)
                t2.circle(80, 225)
                t2.left(180)
                t2.circle(80, 225)
                t2.forward(195)
                
            elif (select_art == "구름"): #구름
                t2.forward(50)
                t2.circle(50, 225)
                t2.left(180)
                t2.circle(70, 225)
                t2.forward(50)
                t2.left(180)
                t2.circle(60, 225)
                t2.forward(60)
                t2.left(45)
                t2.forward(200)

            t2.end_fill()


    elif (select == "Q" or select == "q"): #종료
        break

    else: #잘못된 명령어
        t2.shape("classic")
        t2.write("잘못된 명령어입니다.")