import tkinter
import tkinter.ttk
from DBConn import DBConn

# 함수 만들기 = 테이블행을 클릭하면 동작할 함수
def clickRow(event):
    values = table.item(table.focus()).get('values')
    label.config(text='{}님 데이터를 선택했습니다.'.format(values[0]))


root=tkinter.Tk()
root.title("테이블 그리기")
root.geometry("700x500+50+50")
root.resizable(False, True)
iconData = """
    iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAABHNCSVQICAgIfAhkiAAAALpJREFUWIXtVVsKhDAMHMUT7Y8n9C7+e5g9TvdLqBDTSZqwIh0QBOcR06YFBgZegNIjnv9dxJQQaPK0kC1/SfuyxJRwgNsDUvhUPSe+1nAAWIzhdwHevaQK2fAusGOYEq4ZF4ITgqiD6F0FbNV7RPsLlHNECohef9VP6sAaENqNgkbrHF6H9DF7DJs+j5wC4Fqtdxlq3X5HyroLaG3L1FOESeMxlLQMx12AFtDtbbmO2WI/Bm7ujA8w+AFxiyFEBO6YkgAAAABJRU5ErkJggg==
    """
icon32 = tkinter.PhotoImage(data=iconData)
root.iconphoto(False, icon32)


label = tkinter.Label(root, text='테이블을 그립니다')
label.pack(pady=5) # 레이블과 위쪽 간격


# 테이블과 스크롤 바를 담을 Frame 생성
frame = tkinter.Frame(root)
frame.pack(pady=10, padx=10, fill='both', expand=False) # Frame을 메인 창에 채우도록 설정


# table
table = tkinter.ttk.Treeview(frame,
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
# print(result)

for idx, val in enumerate(result):
    # table.insert('상위', '위치', '데이터')
    table.insert('', 'end', text=str(idx), values=val)

# 위 행 수(data)를 label에 찍어주기
label.config(text="통신 성공, {}개의 데이터를 가져왔습니다.".format(data))

# 테이블 행을 클릭했을 때 동작을 추가해봅니다.
table.bind('<ButtonRelease-1>', clickRow)

# 스크롤 바 (Frame 안에 생성)
scrollbar = tkinter.ttk.Scrollbar(frame, orient="vertical", command=table.yview) # Frame 안에 생성하도록 수정!
# scrollbar.pack(side='right', fill='y') # Frame 안에 pack 할 때는 순서가 중요해

# Frame 안에서 스크롤 바와 테이블 배치
# 스크롤 바를 먼저 오른쪽에 채워서 배치
scrollbar.pack(side='right', fill='y')
# 테이블을 나머지 공간을 채우도록 배치
table.pack(side='left', fill='both', expand=True) # Frame 안에서 왼쪽에 붙고 남은 공간을 채우도록 설정

# 선그리기
line = tkinter.Frame(root, height=1, background="gray")
line.pack(pady=(0, 10), padx=10, fill='x')

#데이터 클릭시 화면 하단에 보여줄 input창들을 묶을 frame
nameFrame = tkinter.Frame(root, height='30')
nameFrame.pack(fill='both', expand=False)

nameLabel = tkinter.Label(nameFrame, text='이름')
nameLabel.grid(row=0, column=0, sticky=tkinter.W, pady=5, padx=5) # 왼쪽 정렬, 상하좌우 여백

nameEntry = tkinter.Entry(nameFrame, width=20)
nameEntry.grid(row=0, column=1, pady=5, padx=5) # 좌우 확장, 상하좌우 여백

# 경고 표시
nameNoticeLabel = tkinter.Label(nameFrame, text='이름은 중복될 수 없습니다. 조심하세요.')
nameNoticeLabel.config(fg='red') # 글자색 변경
nameNoticeLabel.grid(row=0, column=2, pady=5, padx=5) # 좌우 확장, 상하좌우 여백
# 좌우 여백 pady=5, 위아래 여백 padx=5

dataFrame = tkinter.Frame(root, height='30', background='white')
dataFrame.pack(fill='both', expand=False)
# 국어 점수
korean_label = tkinter.Label(dataFrame, text="국어 점수")
korean_label.grid(row=0, column=0, pady=5, padx=5)

korean_entry = tkinter.Entry(dataFrame, width=15) # 점수는 너비를 조금 작게
korean_entry.grid(row=0, column=1, pady=5, padx=5)

# 수학 점수
math_label = tkinter.Label(dataFrame, text="수학 점수")
math_label.grid(row=0, column=3, pady=5, padx=5)

math_entry = tkinter.Entry(dataFrame, width=15)
math_entry.grid(row=0, column=4, pady=5, padx=5)

# 영어 점수
english_label = tkinter.Label(dataFrame, text="영어 점수")
english_label.grid(row=0, column=5, pady=5, padx=5)

english_entry = tkinter.Entry(dataFrame, width=15)
english_entry.grid(row=0, column=6, pady=5, padx=5)

# 입력버튼
saveButton = tkinter.Button(dataFrame, text="저장")
saveButton.grid(row=0, column=7, pady=5, padx=5)
# 초기화 버튼
resetButton = tkinter.Button(dataFrame, text="지우기")
resetButton.grid(row=0, column=8, pady=5, padx=5)

root.mainloop()


