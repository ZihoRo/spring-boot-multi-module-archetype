import os


def eclipse_init():
    os.system("mvn clean eclipse:eclipse")


if __name__ == "__main__":
    eclipse_init()
