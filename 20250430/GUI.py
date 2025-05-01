from tkinter import *
def calc(event):
    try:
        # 입력된 값을 가져와서 계산 시도
        result = eval(entry.get())
        # 계산 성공 시 레이블 업데이트
        label.config(text='계산결과: ' + str(result))
    except Exception as e:
        # 계산 중 에러 발생 시 레이블에 에러 메시지 표시
        label.config(text='오류 발생: ' + str(e))
        print(f"Error evaluating input: {e}") # 디버깅을 위해 콘솔에도 에러 출력

root = Tk()
root.title('간단 계산기') # 제목을 좀 더 명확하게 바꿔봤어
root.geometry('600x300') # 창 크기를 조금 줄여봤어
root.resizable(False, False)

label = Label(root, text='여기에 결과가 나와요') # 초기 메시지를 좀 더 친절하게 바꿔봤어
label.pack(pady=10) # 레이블과 위쪽 간격을 조금 줬어

entry = Entry(root, width=30)
entry.bind('<Return>', calc)
entry.pack(pady=10) # 엔트리와 아래쪽 간격을 조금 줬어

root.mainloop()