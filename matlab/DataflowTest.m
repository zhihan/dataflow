classdef DataflowTest < matlab.unittest.TestCase
    
    methods(Test)
        function testGraphDotString(testcase)
            g = createLinearGraph(4);
            s = g.toDotString;
            s = char(s);
            testcase.verifyEqual(isempty(s), false);
        end
    
        function testForward1(testcase)
            g = createLinearGraph(4);
            r = MySE.Reachable(g);
            inactive = MySE.Inactive([4 3], []);
            b = r.forward(1, inactive);
            testcase.verifyEqual(length(b), 2);
        end
        function testForward2(testcase)
            g = createLinearGraph(5);
            r = MySE.Reachable(g);
            inactive = MySE.Inactive([], [2]);
            b = r.forward(1, inactive);
            testcase.verifyEqual(length(b), 3);
        end
        function testBackward(testcase)
            g = createLinearGraph(5);
            r = MySE.Reachable(g);
            inactive = MySE.Inactive([], [2]);
            b = r.backward(5, inactive);
            testcase.verifyEqual(length(b), 2);
        end
        
        function testTreeDotString(testcase)
            t = createSampleTree;
            s = t.toDotString;
            s = char(s);
            testcase.verifyEqual(isempty(s), false);
        end
        function testTreeGetNode(testcase)
            t = createSampleTree;
            foundB = t.getNode(2);
            testcase.verifyEqual(foundB.id, 2);
            
            try
                b = t.getNode(5);
            catch e%
                b = [];
            end
            testcase.verifyEqual(isempty(b), true);
        end
    end
    
    methods
       
    end
end

function g = createLinearGraph(n)
% Create a linear graph 1->2->3...->i
for i=1:n
    v(i) = MySE.Vertex(i, mat2str(i));
end
f = MySE.GraphFactory();
g = f.make(v);
for i=1:n-1
    g.addEdge(i, i+1);
end
end

function a =createSampleTree()
f = MySE.TreeNodeFactory;
a = f.make(1,'1');
b = f.make(2,'2');
c = f.make(3,'3');
d = f.make(4,'4');
a.addChild(b);
a.addChild(c);
b.addChild(d);
end