import os
import sys


def package():
    size = len(sys.argv)
    if size < 2:
        print("Please enter the environmental parameters, such as dev,daily,pre,online")
    elif size > 2:
        print("Only one parameter will be received.")
    else:
        os.system("git pull")
        os.system("mvn clean package -Dmaven.test.skip=true -U -P%s" % sys.argv[1])


if __name__ == "__main__":
    package()
