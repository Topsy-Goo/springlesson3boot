package com.geekbrains.webapp.springlesson3mvc;

import org.springframework.boot.ExitCodeGenerator;

public class AppExitCodeGenerator implements ExitCodeGenerator
{
    private final int exitCode;

    public AppExitCodeGenerator (int code)  {   exitCode = code;   }

    public int getExitCode()    {   return exitCode;   }
}
