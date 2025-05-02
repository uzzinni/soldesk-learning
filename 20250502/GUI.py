import tkinter
import tkinter.ttk
from DBConn import DBConn

# 함수 만들기 = 테이블행을 클릭하면 동작할 함수
def clickRow(event):
    #print("테이블 행을 클릭했습니다")
    values = table.item(table.focus()).get('values')
    #print(values)
    #print(values[0])
    label.config(text='{}님 데이터를 선택했습니다.'.format(values[0]))


root=tkinter.Tk()
root.title("테이블 그리기")
root.geometry("700x300+50+50")
root.resizable(False, False)

label = tkinter.Label(root, text='테이블을 그립니다')
label.pack()
# table
table = tkinter.ttk.Treeview(root,
                    columns=['name','kor','eng','mat','tot','ave'],
                    displaycolumns=['name','kor','eng','mat','tot','ave']
                    )
table.pack()

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

# 데이터베이스에서 데이터 가져오기
conn = DBConn.conn()
cursor = conn.cursor()
sql = 'SELECT name, kor, eng, mat, tot, ave FROM score'
data = cursor.execute(sql) # 실행 행수
result = cursor.fetchall() # 데이터 얻어오기
print(result)

for idx, val in enumerate(result):
    # table.insert('상위', '위치', '데이터')
    table.insert('', 'end', text=str(idx), values=val)

# 위 행 수(data)를 label에 찍어주기
label.config(text="통신 성공, {}개의 데이터를 가져왔습니다.".format(data))

# 테이블 행을 클릭했을 때 동작을 추가해봅니다.
table.bind('<ButtonRelease-1>', clickRow)

#스크롤 바
scrollbar = tkinter.ttk.Scrollbar(root, orient="vertical", command=table.yview)
scrollbar.pack(side='right', fill='y')
table.configure(yscrollcommand=scrollbar.set)

root.mainloop()


