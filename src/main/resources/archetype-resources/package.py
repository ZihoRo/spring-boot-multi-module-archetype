import os,sys

def package():
    size = len(sys.argv)
    if size < 2:
        print("please input env.")
    elif size > 2:
        print("Only one parameter is received.")
    else:
        os.system("git pull")
        os.system("mvn clean package -Dmaven.test.skip=true -U -P" + sys.argv[1])

if __name__ == "__main__":
    package()