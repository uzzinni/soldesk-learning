import tkinter
import tkinter.ttk
import tkinter.messagebox
import tkinter.simpledialog
from DBConn import ScoreDAO


def saveEntry():
    btmText.config(text='저장을 클릭했습니다.')
    # tkinter.messagebox.showinfo('클릭', '저장을 클릭했습니다.')
    name = nameEntry.get()  # 이름은 1글자 이상이어야 합니다.
    while name is None or len(name) < 1:
        name = tkinter.simpledialog.askstring("이름", '이름을 입력하세요.')
        # tkinter.messagebox.showinfo('입력한 이름', name)
        btmText.config(text='입력한 이름 : {}'.format(name))
    nameEntry.delete(0, tkinter.END)
    nameEntry.insert(0, name)

    #    if len(name) < 1:
    #        # tkinter.messagebox.showinfo('오류', '이름을 확인해주세요.')
    #        # 입력창 만들기 simpledialog askstring:String, askinteger : Integer
    #        name = tkinter.simpledialog.askstring("이름", '이름을 입력하세요.')
    #        tkinter.messagebox.showinfo('입력한 이름', name)

    # 0보다 커야 하고, 100보다 작아야 해요.
    # btmText.config(text=(korEntry.get()).isdigit())
    kor = korEntry.get()
    while not str(kor).isdigit() or not (0 <= int(kor) <= 100):
        kor = tkinter.simpledialog.askinteger(
            minvalue=0, maxvalue=100,
            title='국어점수', prompt='0~100 사이 숫자를 입력하세요')
    korEntry.delete(0, tkinter.END)
    korEntry.insert(0, kor)

    # if 0 <= kor <= 100:
    #    tkinter.messagebox.showinfo('오류','숫자를 확인해주세요.')

    # eng = int(engEntry.get())
    eng = engEntry.get()
    while not str(eng).isdigit() or not (0 <= int(eng) <= 100):
        eng = tkinter.simpledialog.askinteger(
            minvalue=0, maxvalue=100,
            title='영어점수', prompt='0~100 사이 숫자를 입력하세요')
    engEntry.delete(0, tkinter.END)
    engEntry.insert(0, kor)

    # mat = int(matEntry.get())
    mat = matEntry.get()
    while not str(mat).isdigit() or not (0 <= int(mat) <= 100):
        mat = tkinter.simpledialog.askinteger(
            minvalue=0, maxvalue=100,
            title='수학점수', prompt='0~100 사이 숫자를 입력하세요')
    matEntry.delete(0, tkinter.END)
    matEntry.insert(0, kor)

    # 데이터 합치기
    tot = int(kor) + int(eng) + int(mat)
    ave = tot / 3.0
    data = (name, int(kor), int(eng), int(mat), tot, ave)
    # scoreDAO 객체에서 scoreSave 불러오기
    result = dao.scoreSave(data)
    btmText.config(text=result)
    # 화면 갱신 필요
    table.insert('', 'end', text='new', values=data)
    # 엔트리에 입력된 값 초기화 필요
    nameEntry.delete(0, tkinter.END)
    korEntry.delete(0, tkinter.END)
    engEntry.delete(0, tkinter.END)
    matEntry.delete(0, tkinter.END)


# 함수 만들기 = 테이블행을 클릭하면 동작할 함수
def clickRow(event):
    # print("테이블 행을 클릭했습니다")
    values = table.item(table.focus()).get('values')
    # print(values)
    # print(values[0])
    btmText.config(text='{}님 데이터를 선택했습니다.'.format(values[0]))


# 리셋 버튼을 눌렀을 때 동작하는 함수
def entryReset():
    # print('클릭하셨습니다')
    # tkinter.messagebox.showinfo("경고", "reset을 눌렀습니다.")
    # entry 지우기
    # nameEntry.get() 가져오기
    nameEntry.delete(0, tkinter.END)
    korEntry.delete(0, tkinter.END)  # 삭제 -> 지우기
    engEntry.delete(0, tkinter.END)  # 삭제 -> 지우기
    matEntry.delete(0, tkinter.END)  # 삭제 -> 지우기
    # tkinter.messagebox.showinfo("이름", str(len(nameEntry.get()))   )
    # btmText에 글자 바꾸기
    btmText.config(text='데이터를 초기화했습니다')


root = tkinter.Tk()
root.title("테이블 그리기")
root.geometry("700x400+50+50")
root.resizable(True, True)
# 아이콘
# iconData ="실제 아이콘 데이터 = base64"
iconData = """
    iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAABHNCSVQICAgIfAhkiAAAALpJREFUWIXtVVsKhDAMHMUT7Y8n9C7+e5g9TvdLqBDTSZqwIh0QBOcR06YFBgZegNIjnv9dxJQQaPK0kC1/SfuyxJRwgNsDUvhUPSe+1nAAWIzhdwHevaQK2fAusGOYEq4ZF4ITgqiD6F0FbNV7RPsLlHNECohef9VP6sAaENqNgkbrHF6H9DF7DJs+j5wC4Fqtdxlq3X5HyroLaG3L1FOESeMxlLQMx12AFtDtbbmO2WI/Bm7ujA8w+AFxiyFEBO6YkgAAAABJRU5ErkJggg==
    """
icon = tkinter.PhotoImage(data=iconData)
root.iconphoto(False, icon)

label = tkinter.Label(root, text='테이블을 그립니다')
label.pack()

# 하단에 정보 보여주기 창 만들기
btmFrame = tkinter.Frame(root, height=40)
btmFrame.pack(side='bottom', fill='both')
btmLabel = tkinter.Label(btmFrame, text='[메시지]')
btmLabel.grid(row=0, column=0)
btmText = tkinter.Label(btmFrame, text=' 프로그램이 실행되었습니다')
btmText.grid(row=0, column=1)

# 프레임을 만들어서 table과 스크롤 바를 붙이겠습니다.
tableFrame = tkinter.Frame(root)
tableFrame.pack(pady=10, padx=10, fill='both', expand=False)

# table
table = tkinter.ttk.Treeview(tableFrame,
                             columns=['name', 'kor', 'eng', 'mat', 'tot', 'ave'],
                             displaycolumns=['name', 'kor', 'eng', 'mat', 'tot', 'ave']
                             )
# table.pack() 아래에서 설정해줍니다.

# 각 컬럼 설정
table.column('#0', width=50)  # 첫번째 열 속성
table.heading('#0', text='번호')  # 첫번째

table.column('#1', width=100, anchor='center')  # 첫번째 열 속성
table.heading('name', text='이름')  # 첫번째

table.column("#2", width=100, anchor='center')  # 나침반 방위
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
dao = ScoreDAO()
result = dao.scoreList()  # 데이터 얻어오기
data = len(result)  # 행수
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
line.pack(padx=10, fill='both')  # fill='both' -> 할당 공간을 모두 사용합니다.
# fill = 'x' 가로방향만 확장
# fill = 'y' 세로방향만 확장     fill='none' 확장하지 않음.

# line2 = tkinter.Frame(root, height=1, background='red')
# line2.pack(fill='x')

# 데이터 추가 입력창을 만들겠습니다.
# 이름 : ________
# 국어 : ____ 영어 : _____ 수학 : _____ [저장] [초기화]
nameFrame = tkinter.Frame(root, height=50)
nameFrame.pack(padx=10, fill='both')

# 이름 라벨
nameLabel = tkinter.Label(nameFrame, text='이름')
nameLabel.grid(row=0, column=0, padx=5, pady=5, sticky=tkinter.W)
# 이름 entity
nameEntry = tkinter.Entry(nameFrame, width=30)
nameEntry.grid(row=0, column=1, padx=5, pady=5)

nameNoticeLabel = tkinter.Label(nameFrame, text='이름이 중복되면 위험합니다.')
nameNoticeLabel.config(foreground='red')
nameNoticeLabel.grid(row=0, column=2)

# 점수 입력 프레임
scoreFrame = tkinter.Frame(root, height=50)
scoreFrame.pack(fill='both', padx=10)

# 국어 라벨
korLabel = tkinter.Label(scoreFrame, text="국어")
korLabel.grid(row=0, column=0, padx=5, pady=5)
# 국어 엔트리
korEntry = tkinter.Entry(scoreFrame, width=10)
korEntry.grid(row=0, column=1, padx=10)

# 영어 라벨
engLabel = tkinter.Label(scoreFrame, text='영어')
engLabel.grid(row=0, column=2)
# 영어 엔트리
engEntry = tkinter.Entry(scoreFrame, width=10)
engEntry.grid(row=0, column=3, padx=10)

# 수학 라벨
matLabel = tkinter.Label(scoreFrame, text='수학')
matLabel.grid(row=0, column=4)
# 수학 엔트리
matEntry = tkinter.Entry(scoreFrame, width=10)
matEntry.grid(row=0, column=5, padx=10)

# 저장버튼
saveButton = tkinter.Button(
    scoreFrame, text='저장', width=20, command=saveEntry)
saveButton.grid(row=0, column=6, padx=10)
# 리셋버튼
resetButton = tkinter.Button(
    scoreFrame, text='리셋', width=10, foreground='red', command=entryReset)
resetButton.grid(row=0, column=7)

root.mainloop()


