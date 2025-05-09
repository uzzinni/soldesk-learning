import tkinter.ttk
from ttkwidgets import CheckboxTreeview  # 설치 필요
import tkinter.messagebox
import tkinter.simpledialog
import tkinter.filedialog
from DBConn import ScoreDAO


def delete():
    list = table.get_checked()
    # 클릭된 사용자가 없다면 == len(list)
    if len(list) > 0:
        names = []
        for i in list:
            # print(table.item(i)['values'][0])
            names.append(table.item(i)['values'][0])

        deleteLabel.config(text=names)
        dao.scoreDelete(names)

        # 화면 갱신 1. checked된 list를 treeview에서 삭제하기
        for i in list:
            table.delete(i)

    else:
        tkinter.messagebox.showinfo(title='오류', message='선택된 사용자가 없습니다.')

    names = []


def openFile():
    fileName = tkinter.filedialog.askopenfilename(
        initialdir='/',  # 최상위 루트 c:\
        title='파일 선택',
        filetypes=(('텍스트 파일', '*.txt'), ('모든 파일', '*.*'))
    )
    # 파일 내용 보기(임시)
    readFile = open(fileName, 'r')
    tkinter.messagebox.showinfo(title='읽음', message=readFile.read())


def saveEntry():
    # 저장버튼을 눌렀을 때 저장하겠냐고 물어보기
    result = tkinter.messagebox.askyesno('저장',
                                         ('신규 저장하시겠습니까?' if check.get() == 'insert' else '수정하시겠습니까?'))
    # print(result) # True/False # print(check.get()) # insert/edit
    if result:
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

        # btmText.config(text=(korEntry.get()).isdigit())
        kor = korEntry.get()
        while not str(kor).isdigit() or not (0 <= int(kor) <= 100):
            kor = tkinter.simpledialog.askinteger(minvalue=0, maxvalue=100,
                                                  title='국어점수', prompt='0~100 사이 숫자를 입력하세요')
        korEntry.delete(0, tkinter.END)
        korEntry.insert(0, kor)

        # if 0 <= kor <= 100:
        #    tkinter.messagebox.showinfo('오류','숫자를 확인해주세요.')

        eng = engEntry.get()
        while not str(eng).isdigit() or not (0 <= int(eng) <= 100):
            eng = tkinter.simpledialog.askinteger(
                minvalue=0, maxvalue=100,
                title='영어점수', prompt='0~100 사이 숫자를 입력하세요')
        engEntry.delete(0, tkinter.END)
        engEntry.insert(0, kor)

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
        # data = (name, int(kor), int(eng), int(mat), tot, ave)
        # scoreDAO 객체에서 scoreSave 불러오기
        if check.get() == 'insert':
            result = dao.scoreSave((name, int(kor), int(eng), int(mat), tot, ave))
        else:
            # 수정을 클릭했을 때 DAO에 메소드 만들어줘야 합니다.
            result = dao.scoreEdit((int(kor), int(eng), int(mat), tot, ave, name))
        btmText.config(text=result)
        # 화면 갱신 필요 -> table을 지우고 다시 만들기 필요
        # table.insert('', 'end', text='new', values=data)
        result = dao.scoreList()  # 데이터 얻어오기
        data = len(result)  # 행수
        # treeview 내부 데이터 삭제하기
        for i in table.get_children():
            table.delete(i)

        for idx, val in enumerate(result):
            # table.insert('상위', '위치', '데이터')
            table.insert('', 'end', text=str(idx), values=val)

        # 위 행 수(data)를 label에 찍어주기
        btmText.config(text='추가했습니다' if check.get() == 'insert' else '수정하였습니다')
        # 엔트리에 입력된 값 초기화 필요
        entryReset()

    else:
        print("취소를 눌렀습니다.")


# 함수 만들기 = 테이블행을 클릭하면 동작할 함수
def clickRow(event):
    # print("테이블 행을 클릭했습니다")
    values = table.item(table.focus()).get('values')
    # print(values)
    btmText.config(text='{}님 데이터를 선택했습니다.'.format(values[0]))
    # 클릭한 값을 가져와서 Entry에 출력하기;
    entryReset()  # 이것을 추가했습니다.
    nameEntry.insert(0, values[0])
    korEntry.insert(0, values[1])
    engEntry.insert(0, values[2])
    matEntry.insert(0, values[3])
    radioEdit.select()  # 추가 :::  수정을 선택하게 바꿉니다.
    saveButton.config(text="{} 수정".format(values[0]), background='red')
    # 여기에 추가하겠습니다.


# 리셋 버튼을 눌렀을 때 동작하는 함수
def entryReset():
    # tkinter.messagebox.showinfo("경고", "reset을 눌렀습니다.")
    nameEntry.delete(0, tkinter.END)  # 이름 지우기
    korEntry.delete(0, tkinter.END)  # 국어 점수 지우기
    engEntry.delete(0, tkinter.END)  # 영어 점수 지우기
    matEntry.delete(0, tkinter.END)  # 수학 점수 지우기
    # tkinter.messagebox.showinfo("이름", str(len(nameEntry.get()))   )
    btmText.config(text='데이터를 초기화했습니다')
    radioInsert.select()  # 추가 ::: 리셋을 누르면 저장버튼으로 변경하기
    saveButton.config(text='저장', background='blue', foreground='white')


# 그래픽 시작
root = tkinter.Tk()
root.title("테이블 그리기")
root.geometry("700x450+50+50")  # 크기, 여백
root.resizable(True, True)  # 크기 조정 가능
# 아이콘
# iconData ="실제 아이콘 데이터 = base64"
iconData = """
    iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAABHNCSVQICAgIfAhkiAAAALpJREFUWIXtVVsKhDAMHMUT7Y8n9C7+e5g9TvdLqBDTSZqwIh0QBOcR06YFBgZegNIjnv9dxJQQaPK0kC1/SfuyxJRwgNsDUvhUPSe+1nAAWIzhdwHevaQK2fAusGOYEq4ZF4ITgqiD6F0FbNV7RPsLlHNECohef9VP6sAaENqNgkbrHF6H9DF7DJs+j5wC4Fqtdxlq3X5HyroLaG3L1FOESeMxlLQMx12AFtDtbbmO2WI/Bm7ujA8w+AFxiyFEBO6YkgAAAABJRU5ErkJggg==
    """
icon = tkinter.PhotoImage(data=iconData)
root.iconphoto(False, icon)

# 20250508 파이선 데이터 분석
# 메뉴 만들기
menuBar = tkinter.Menu(root)

fileMenu = tkinter.Menu(menuBar, tearoff=False)
fileMenu.add_command(label="새 파일")
fileMenu.add_command(label="열기", command=openFile)  # 파일열기
fileMenu.add_command(label="인쇄")
fileMenu.add_separator()  # 구분선
fileMenu.add_command(label="닫기", command=root.quit)  # 닫기 기능

helpMenu = tkinter.Menu(menuBar, tearoff=False)
helpMenu.add_command(label='도움말')
helpMenu.add_command(label='만든사람')

menuBar.add_cascade(label="file", menu=fileMenu)
menuBar.add_cascade(label="help", menu=helpMenu)
root.config(menu=menuBar)

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
table = CheckboxTreeview(tableFrame,
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

for idx, val in enumerate(result):
    # table.insert('상위', '위치', '데이터')
    table.insert('', 'end', text=str(idx + 1), values=val)

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
# 수정 삭제 라디오 버튼 = grid
# check 변수             0 수정 0 추가를 선택하면 선택한 값이 저장될 변수
check = tkinter.StringVar()
radioEdit = tkinter.Radiobutton(nameFrame, text='수정', value='edit', variable=check)
radioInsert = tkinter.Radiobutton(nameFrame, text='추가', value='insert', variable=check, command=entryReset)
radioEdit.grid(row=0, column=3)
radioInsert.grid(row=0, column=4)
radioInsert.select()

# 점수 입력 프레임
scoreFrame = tkinter.Frame(root, height=50)
scoreFrame.pack(fill='both', padx=10)

# 국어 라벨
korLabel = tkinter.Label(scoreFrame, text="국어")
korLabel.grid(row=0, column=0, padx=5, pady=5)
# 국어 엔트리
korEntry = tkinter.Entry(scoreFrame, width=10)
korEntry.grid(row=0, column=1, padx=5)
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
saveButton = tkinter.Button(scoreFrame, text='저장', width=20, command=saveEntry)
saveButton.grid(row=0, column=6, padx=10)
# 리셋버튼
resetButton = tkinter.Button(scoreFrame, text='리셋', width=10, foreground='red', command=entryReset)
resetButton.grid(row=0, column=7)

# 선그리기
line = tkinter.ttk.Separator(root, orient="horizontal")
line.pack(padx=10, pady=(5, 0), fill='both')

# 삭제
deleteFrame = tkinter.Frame(root, height=30)
deleteFrame.pack(fill='both')
deleteLabel = tkinter.Label(deleteFrame, text='')
deleteLabel.grid(row=0, column=0)
deleteBtn = tkinter.Button(deleteFrame, text='삭제', width=10, foreground='red', command=delete)
deleteBtn.grid(row=0, column=1)

root.mainloop()