# class
class Human:
    def eat(thing):
        print("%s를 먹습니다." % thing)

    def sleep(num=1):
        print('Zzzzz')

# end class
human = Human
print(type(human))
print(human.sleep(1))
print(human.eat("무엇"))

