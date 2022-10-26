import traceback
from unittest import result
import PIL
from zeep import Client
import PIL.Image as Image
from datetime import datetime
import io
accessTool = Client('http://localhost:7000/RAT?wsdl').service

myOption = 0

 #This is the whole menu#
while(myOption != 4):
    print("The remote access tool allows you to choose among 4 options:\n")
    print("1 ==> Get the list of processes running in the remote system\n")
    print("2 ==> Get the screenshot of the remote system\n")
    print("3 ==> Reboot the remote system\n")
    print("4 ==> Quit\n")
    myOption = int(input("\My option is: "))


    if myOption == 1:
        if not accessTool.getRunningProcess():
            print("\n\tOperation failed!\n")
        else:
            try:
                arr = accessTool.getRunningProcess()
                for x in arr:
                    print(x)
            except:
                    print("Operation failed ...")

    elif myOption == 2:
        try:
            myImageByte = bytearray(accessTool.getScreenShot())
            myImage = Image.open(io.BytesIO(myImageByte))
            now = datetime.now()
            path = r'C:\Users\abdor\OneDrive\Desktop\Homework2\RAT\src\main\python\consumerShare\MyScreenShoot_'
            myImageName = path+ now.strftime("%Y_%m_%d_%H_%M_%S") + ".png"
            myImage.save(myImageName)
            print("\n\tOperation successful!\n")

        except:
            print("\n\t The screenshoot operation failed, please try again ....\n")
    elif myOption == 3:
        if not accessTool.reboot():
            print("\n\tOperation failed!\n")
        else:
            print("\n\tOperation successful!\n")
            myOption = 4
    elif myOption ==4:
        print("\n\tYou quit the RAT system.\n")

    else:
        print("\n\t This is a wrong option, please try again!\n")


    

