from tkinter import *
from tkinter import font
import random
import calendar

def end():
    root.destroy()
    
def delete():
   
    ent.delete(0,END)
    
def total():
    ent.delete(0,END)
    if (chk1.get() == 1) and ( chk2.get() == 1) and ( chk3.get() == 1) :
        lbltotal.configure(text="옥수수 피자, 쉬프림 피자, 콤비네이션 피자")
        total = 22000 + 31000+ 19000        
    elif (chk1.get() == 1) and ( chk2.get() == 1) and ( chk3.get() == 0):
        lbltotal.configure(text="옥수수 피자, 쉬프림 피자")
        total = 22000+31000
        
    elif (chk1.get() == 1) and ( chk2.get() == 0) and ( chk3.get() == 1):
        lbltotal.configure(text="옥수수 피자, 콤비네이션 피자")
        total = 22000 + 19000        
    elif (chk1.get() == 0) and ( chk2.get() == 1) and ( chk3.get() == 1):
        lbltotal.configure(text="쉬프림 피자, 콤비네이션 피자")
        total = 31000+19000            
    elif (chk1.get() == 1) and ( chk2.get() == 0) and ( chk3.get() == 0):
        lbltotal.configure(text="옥수수 피자")
        total = 22000    
    elif (chk1.get() == 0) and ( chk2.get() == 1) and ( chk3.get() == 0):
        lbltotal.configure(text="쉬프림피자")
        total = 31000        
    elif (chk1.get() == 0) and ( chk2.get() == 0) and ( chk3.get() == 1):
        lbltotal.configure(text="콤비네이션 피자")
        total = 19000        
    else:
        lbltotal.configure(text="")
        total = 0
        
    ent.insert(0,total)
    
def show():
    new_gui = Tk()
    new_gui.config(background="white")
    new_gui.title("달력보기")
    new_gui.geometry("500x500")
    cal_content = calendar.calendar(2021)
    cal_year = Label( new_gui, text = cal_content)    
    cal_year.pack()
    new_gui.mainloop()

root = Tk()
root.title("피자주문하기 ")
root.geometry("800x650")

font = font.Font(family = "굴림체", size = 14)


photo1 = PhotoImage(file = "옥수수피자.png")
photo2 = PhotoImage(file = "쉬프림핫치킨피자.png")
photo3 = PhotoImage(file = "콤비네이션피자.png")

chk1 = IntVar()
chk2 = IntVar()
chk3 = IntVar()

#레이블 만들기
lbl1 = Label(root, text="HS 피자스쿨에 오신것을 환영합니다.",font = "바탕체 18 bold")
lbl1.grid(row = 0, column = 0)

lbl2 = Label(root, text="--------------- 메뉴 -----------------",font=font)
lbl2.grid(row = 1, column = 0)

#---1번째 피자
chb1 = Checkbutton(root, text = "옥수수피자/22000", font=font, variable = chk1, command = total)
chb1.grid(row = 2, column = 0)

lbl4 = Label(root,image=photo1)
lbl4.grid(row = 3, column = 0)

#---2번째 피자
chb2 = Checkbutton(root, text = "쉬프림피자/31000원", font=font, variable = chk2, command = total)
chb2.grid(row = 4, column = 0)

lbl6 = Label(root,image = photo2)
lbl6.grid(row = 5, column = 0)

#---3번째 피자

chb3 = Checkbutton(root, text = "콤비네이션피자/19000원", font=font, variable = chk3, command = total)
chb3.grid(row =6, column = 0)

lbl8 = Label(root,image = photo3)
lbl8.grid(row = 7, column = 0)


# 지우기/종료하기 버튼 만들기
btn = Button(root, text="지우기", width=10, height=2,
              bg = "gray", fg ="white",font=font, command = delete)
btn.grid(row = 3, column = 1)
btn = Button(root, text="종료하기", font=font, width=10, height=2,
              bg = "gray", fg ="white", command = end)
btn.grid(row = 3, column = 2)

# 총금액 
lbl9 = Label(root, text="[총금액]",font=font)
lbl9.grid(row = 4, column = 1)

ent = Entry(root,font=font)
ent.grid(row = 4, column = 2)

lbltotal = Label(root,text="-"*80, font=font)
lbltotal.place(x=10, y=550)

# 주문한 피자
lbltotal = Label(root,text="[주문한 피자]",font=font)
lbltotal.place(x=10, y=570)

lbltotal = Label(root,text="",font=font,fg="red")
lbltotal.place(x=10, y=600)

# 달력보기 버튼
btn2= Button(root, text="달력보기", bg = "gray", fg ="white", command =show)
btn2.place(x=600, y=600)

root.mainloop()
