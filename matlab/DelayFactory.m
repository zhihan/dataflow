classdef DelayFactory < handle
    properties
        n = 0
        graph  % handle to the graph obj
        types = zeros(10000,1);
    end
    
    methods
        function obj = DelayFactory(g)
            obj.graph = g;
        end
        function id = create(obj, ~)
            obj.n = obj.n + 1;
            %obj.types(obj.n) = t;
            id = obj.n;
        end
        
        function objs = finalize(obj)
            x = {'x'};
            y = repmat(x, obj.n, 1);
            objs = obj.graph.newVarNodes(y);
        end
    end
end