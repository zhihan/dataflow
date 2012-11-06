runner = matlab.unittest.TestRunner.withTextOutput;
suite = matlab.unittest.TestSuite.fromClass(?DataflowTest);
runner.run(suite)