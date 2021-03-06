%
%javaaddpath /local-ssd/zhan/scala-2.9.2/lib/scala-library.jar

function setup()

clear java;

[~,name] = system('hostname');

switch deblank(lower(name))
    % Add scala and antlr runtime to the java classpath
    case 'ah-zhan' % My Windows PC at work
        javaaddpath('C:\scala\lib\scala-library.jar');
        %javaaddpath(fullfile('C:\jars',  'antlr-3.5-complete-no-st3.jar'));
    case 'zhan-deb7-64' % My linux PC at work
        javaaddpath(fullfile('/local-ssd/zhan/scala-2.10.4', ...
            'lib','scala-library.jar'));
    case 'bo-songs-mbp.home' % My macbook pro
        javaaddpath(fullfile('/Users/zhihan/scala-2.10.4', ...
            'lib','scala-library.jar'));
        
    otherwise
        if strcmpi(computer, 'maci64')
            javaaddpath(fullfile('/Users/zhihan/scala-2.10.4', ...
                'lib','scala-library.jar'));
        else
            error('Don''t know what to do yet')
        end
end

javaaddpath(fullfile(pwd, 'se_2.10-1.0.jar'));
end


