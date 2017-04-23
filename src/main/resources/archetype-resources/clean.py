#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
import fnmatch
import os


def eclipse_clean():
    os.system("mvn clean eclipse:clean")


def idea_clean():
    rm("./", ["*.iml", "*.ipr", "*.iws", ".idea_modules", ".idea"], True)


def rm(root, patterns, recursive=False):
    for path, sub_dirs, files in os.walk(root):
        for pattern in patterns:
            for filename in fnmatch.filter(files, pattern):
                os.remove(os.path.join(path, filename))
            if recursive:
                for dirname in fnmatch.filter(sub_dirs, pattern):
                    rm(os.path.join(path, dirname), ["*"], recursive)
                    os.rmdir(os.path.join(path, dirname))
            else:
                try:
                    for dirname in fnmatch.filter(sub_dirs, pattern):
                        os.rmdir(os.path.join(path, dirname))
                except OSError:
                    print("Directory not empty:'%s'" % os.path.join(path, dirname))


def clean():

    for path, sub_dirs, files in os.walk(root):



if __name__ == "__main__":
    idea_clean()
    eclipse_clean()
