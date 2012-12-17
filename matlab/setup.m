%
javaaddpath /local-ssd/zhan/scala-2.9.2/lib/scala-library.jar
%javaaddpath C:\scala-2.9.2\lib\scala-library.jar
javaaddpath(fullfile(pwd, 'se.jar'));

[parent, ~] = fileparts(pwd);

javaaddpath(fullfile('/local-ssd/zhan/jars', ...
    'antlr-3.4-complete-no-antlrv2.jar'));


