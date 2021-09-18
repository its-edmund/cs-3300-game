import os
import platform
from zipfile import ZipFile
from urllib.request import urlopen

if platform.system() == 'Linux':
    url = "https://download2.gluonhq.com/openjfx/17.0.0.1/openjfx-17.0.0.1_linux-x64_bin-sdk.zip"
elif platform.system() == 'Windows':
    url = "https://download2.gluonhq.com/openjfx/17.0.0.1/openjfx-17.0.0.1_windows-x64_bin-sdk.zip"
elif platform.system() == 'Darwin':
    url = "https://download2.gluonhq.com/openjfx/17.0.0.1/openjfx-17.0.0.1_osx-x64_bin-sdk.zip"

zipresp = urlopen(url)
temp_javafx_file = open("tempfile.zip", "wb")
temp_javafx_file.write(zipresp.read())
temp_javafx_file.close()

javafx_file = ZipFile("tempfile.zip")
javafx_file.extractall(path = 'lib/')
javafx_file.close()

os.remove("tempfile.zip")