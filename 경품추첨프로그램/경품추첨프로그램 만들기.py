from tkinter import *
import random

def draff_check():
    img = ["tv","노트북","스마트폰","꽝"]
    w = 600
    h = 400

    product = random.choice(img)

    if product == "tv":
        lbl.configure(text="tv당첨")
        lbl2 = Label(root, image = photo1, width = w, height=h)
        
    elif product == "노트북":
        lbl.configure(text="노트북당첨")
        lbl2 = Label(root, image = photo2, width = w, height=h)
    
    elif product == "스마트폰":
        lbl.configure(text="스마트폰당첨")
        lbl2 = Label(root, image = photo3, width = w, height=h)
    else:
        lbl.configure(text="꽝~~~")
        lbl2 = Label(root, image = photo4, width = w, height=h)
    lbl2.place(x=200, y=10)            
    

root = Tk()
root.title("경품 추첨하기 ")
root.geometry("800x500")
root.configure(bg="yellow")


photo1 = PhotoImage(file = "tv.png")
photo2 = PhotoImage(file = "노트북.png")
photo3 = PhotoImage(file = "스마트폰.png")
photo4 = PhotoImage(file = "꽝.png")

#버튼 만들기
btn = Button(root, text="추첨하기", width=20, height=2,
              bg = "blue", fg ="yellow", command = draff_check)
btn.place(x=50, y=40)

#레이블 만들기
lbl = Label(root, text="?")
lbl.place(x=50, y=100)

root.mainloop()
