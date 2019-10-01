call runcrud
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo RUNCRUD has errors - breaking work
goto fail

:runbrowser
start "C:\Users\pc\Desktop\chrome.exe" http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.