import tkinter
import tkinter.ttk
import tkinter.messagebox
import TableCreate
from DBConn import ScoreDAO

# 함수 만들기 = 테이블행을 클릭하면 동작할 함수
def clickRow(event):
    values = table.item(table.focus()).get('values')
    btmText.config(text='{}님 데이터를 선택했습니다.'.format(values[0]))

#리셋 버튼을 눌렀을 때 동작하는 함수
def entryReset():
    # tkinter.messagebox.showinfo("경고", "reset을 눌렀습니다.")
    # entry 지우기
    # nameEntry.get() 가져오기
    nameEntry.delete(0, tkinter.END) # 데이터를 끝까지 지우기
    korEntry.delete(0, tkinter.END)
    engEntry.delete(0, tkinter.END)
    matEntry.delete(0, tkinter.END)
    # tkinter.messagebox.showinfo("완료", '초기화 했습니다')
    btmText.config(text='데이터를 초기화 했습니다')

root=tkinter.Tk()
root.title("테이블 그리기")
root.geometry("700x400+50+50")
root.resizable(True, True)
#아이콘
# iconData ="실제 아이콘 데이터 = base64"
iconData = """
    iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAABHNCSVQICAgIfAhkiAAAALpJREFUWIXtVVsKhDAMHMUT7Y8n9C7+e5g9TvdLqBDTSZqwIh0QBOcR06YFBgZegNIjnv9dxJQQaPK0kC1/SfuyxJRwgNsDUvhUPSe+1nAAWIzhdwHevaQK2fAusGOYEq4ZF4ITgqiD6F0FbNV7RPsLlHNECohef9VP6sAaENqNgkbrHF6H9DF7DJs+j5wC4Fqtdxlq3X5HyroLaG3L1FOESeMxlLQMx12AFtDtbbmO2WI/Bm7ujA8w+AFxiyFEBO6YkgAAAABJRU5ErkJggg==
    """
icon = tkinter.PhotoImage(data=iconData)
root.iconphoto(False, icon)

label = tkinter.Label(root, text='테이블을 그립니다')
label.pack()

#프레임을 만들어서 table과 스크롤 바를 붙이겠습니다.
tableFrame = tkinter.Frame(root)
tableFrame.pack(pady=10, padx=10, fill='both', expand=False)

# menu ##########################
menuBar = tkinter.Menu(root)
fileMenu = tkinter.Menu(menuBar, tearoff=False)
fileMenu.add_command(label="새 파일",) # '새 파일' 항목 추가
fileMenu.add_command(label="열기",)   # '열기' 항목 추가
fileMenu.add_separator() # 구분선 추가
fileMenu.add_command(label="끝내기", command=root.quit) # (창 닫기)
menuBar.add_cascade(label="파일", menu=fileMenu)
#바텀 설정 #########################################
btmFrame = tkinter.Frame(root, height=30)
btmFrame.pack(side='bottom', fill='both', expand=False)
btmLabel = tkinter.Label(btmFrame, text="[메시지]")
btmLabel.grid(row=0, column=0)
btmText = tkinter.Label(btmFrame, text=" 프로그램이 실행되었습니다.")
btmText.grid(row=0, column=1)

root.config(menu=menuBar)
# tableCreate
table = TableCreate.tableCreate(tableFrame)

# 데이터베이스에서 데이터 가져오기
dao = ScoreDAO()
result = dao.scoreList() # 데이터 얻어오기
data = len(result) # 행수
# print(result)

for idx, val in enumerate(result):
    # table.insert('상위', '위치', '데이터')
    table.insert('', 'end', text=str(idx), values=val)

# 위 행 수(data)를 label에 찍어주기
btmText.config(text="통신 성공, {}개의 데이터를 가져왔습니다.".format(data))

# 테이블 행을 클릭했을 때 동작을 추가해봅니다.
table.bind('<ButtonRelease-1>', clickRow)

# 스크롤 바(이제 수정)
scrollbar = tkinter.ttk.Scrollbar(tableFrame, orient="vertical", command=table.yview)
scrollbar.pack(side='right', fill='y')
table.pack(side='left', fill="both", expand=True)

# 2025-05-02 선그리기
line = tkinter.ttk.Separator(root, orient="horizontal")
line.pack(padx=10, fill='both') # fill='both' -> 할당 공간을 모두 사용합니다.
# fill = 'x' 가로 방향만 확장
# fill = 'y' 세로 방향만 확장     fill='none' 확장하지 않음.

# line2 = tkinter.Frame(root, height=1, background='red')
# line2.pack(fill='x')

# 데이터 추가 입력창을 만들겠습니다.
# 이름 : ________
# 국어 : ____ 영어 : _____ 수학 : _____ [저장] [초기화]
nameFrame = tkinter.Frame(root, height=50)
nameFrame.pack(padx=10, fill='both')

#이름 라벨
nameLabel = tkinter.Label(nameFrame, text='이름')
nameLabel.grid(row=0, column=0, padx=5, pady=5, sticky=tkinter.W)
#이름 entity
nameEntry = tkinter.Entry(nameFrame, width=30)
nameEntry.grid(row=0, column=1, padx=5, pady=5)

nameNoticeLabel = tkinter.Label(nameFrame,text='이름이 중복되면 위험합니다.')
nameNoticeLabel.config(foreground='red')
nameNoticeLabel.grid(row=0, column=2)

#점수 입력 프레임
scoreFrame = tkinter.Frame(root, height=50)
scoreFrame.pack(fill='both', padx=10)

#국어 라벨
korLabel = tkinter.Label(scoreFrame, text="국어")
korLabel.grid(row=0, column=0)
#국어 엔트리
korEntry = tkinter.Entry(scoreFrame, width=10)
korEntry.grid(row=0, column=1, padx=10)

#영어 라벨
engLabel = tkinter.Label(scoreFrame, text='영어')
engLabel.grid(row=0, column=2)
#영어 엔트리
engEntry = tkinter.Entry(scoreFrame, width=10)
engEntry.grid(row=0, column=3, padx=10)

#수학 라벨
matLabel = tkinter.Label(scoreFrame, text='수학')
matLabel.grid(row=0, column=4)
#수학 엔트리
matEntry = tkinter.Entry(scoreFrame, width=10)
matEntry.grid(row=0, column=5, padx=10)

# 저장 버튼
saveButton = tkinter.Button(scoreFrame, text='저장', width=20, background='blue')
saveButton.grid(row=0, column=6, padx=10)
# 리셋 버튼
resetButton = tkinter.Button(
    scoreFrame, text='리셋', width=10, foreground='red', command=entryReset)
resetButton.grid(row=0, column=7)


root.mainloop()


