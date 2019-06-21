"C:\Program Files\Java\jdk1.8.0_111\bin\keytool" -genkey -keystore myKeystore -alias Stephan
REM First Google Password

"C:\Program Files\Java\jdk1.8.0_111\bin\jarsigner" -keystore myKeystore "FingerPrint System.jar" Stephan
REM First Google Password

pause