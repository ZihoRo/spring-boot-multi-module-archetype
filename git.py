import os
import sys


def git_add_all():
    size = len(sys.argv)
    os.system("git add -A")


def git_commit_message():
    size = len(sys.argv)
    if size < 2:
        print("Please enter the commit message")
    elif size > 2:
        print("Only one parameter will be received.")
    else:
        os.system("git commit -m \"%s\"" % sys.argv[1])


def git_push():
    os.system("git push -u")


if __name__ == "__main__":
    git_add_all()
    git_commit_message()
    git_push()
