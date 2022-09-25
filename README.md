# Filtration

## Goal?
Filtration is used to model information which is available in given timepoint, it's a fundamental concept in stochastic process. Strategy is the transformation for random variable so the signal of strategy is naturally a random variable. This project is aim to build end-to-end experiment system from price process generating, calibration to the implementation of trading strategy.

## Run
```
mvn clean
mvn package
cd filtration-runtime/target
java -jar -Dstrategy=com.chunhuiwu.filtration.strategy.PenetrateStrategy filtration-runtime-1.0-SNAPSHOT.jar 
```

## To Do
1. Useful and interesting stochastic Process simulation
2. Option pricing model
3. model calibration, parameter estimation
4. Risk Management module
5. Backtesting module
