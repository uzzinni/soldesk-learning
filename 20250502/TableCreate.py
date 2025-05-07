import tkinter
import tkinter.ttk
def tableCreate(Frame):
    table = tkinter.ttk.Treeview(Frame,
                    columns=['name','kor','eng','mat','tot','ave'],
                    displaycolumns=['name','kor','eng','mat','tot','ave']
                    )
    #각 컬럼 설정
    table.column('#0', width=50) # 첫번째 열 속성
    table.heading('#0', text='번호') # 첫번째

    table.column('#1', width=100, anchor='center') # 첫번째 열 속성
    table.heading('name', text='이름') # 첫번째

    table.column("#2", width=100, anchor='center') # 나침반 방위
    table.heading('kor', text='국어점수')

    table.column("#3", width=100, anchor='center')
    table.heading('eng', text='영어점수')

    table.column("#4", width=100, anchor='center')
    table.heading('mat', text='수학점수')

    table.column("#5", width=100, anchor='center')
    table.heading('tot', text='총점')

    table.column("#6", width=100, anchor='center')
    table.heading('ave', text='평균')
    return table