%
%javaaddpath /local-ssd/zhan/scala-2.9.2/lib/scala-library.jar

function setup()

clear java;

[~,name] = system('hostname');

switch deblank(name)
    % Add scala and antlr runtime to the java classpath
    case 'ah-zhan' % My Windows PC at work
        javaaddpath('C:\scala-2.9.2\lib\scala-library.jar');
        javaaddpath(fullfile('C:\jars',  'antlr-3.5-complete-no-st3.jar'));
    case 'zhan-deb6-64' % My linux PC at work
        javaaddpath(fullfile('/local-ssd/zhan/scala-2.9.2', ...
            'lib','scala-library.jar'));
        javaaddpath(fullfile('/local-ssd/zhan/jars', ...
            'antlr-3.5-complete-no-st3.jar'));
    otherwise
        error('Don''t know what to do yet')
end

javaaddpath(fullfile(pwd, 'se_2.9.2-1.0.jar'));


