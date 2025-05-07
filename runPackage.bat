cls
@echo off

set "version=1.0.3"

:設置窗口字體顏色
color 0a

:設置窗口標題
TITLE Running package

echo Building project with Maven...

call mvn clean package

if %errorlevel% neq 0 (
  echo Maven build failed!
  pause
  exit /b
)

echo Running jpackage...

call jpackage^
    --type msi^
    --input target^
    --name TheDoorOfDestiny^
    --main-jar my2dgame-%version%.jar^
    --main-class com.example.Main^
    --java-options "--enable-preview"^
    --icon src\main\resources\icon\smile.ico^
    --win-shortcut^
    --win-upgrade-uuid "90f60072-30b1-4c92-8a5f-5316c10ad418"^
    --win-dir-chooser^
    --win-per-user-install^
    --win-menu ^
    --app-version %version%
    
echo jpackage finished.

pause