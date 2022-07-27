@echo off
mvn clean test org.pitest:pitest-maven:mutationCoverage && start "" "target\pit-reports\html2\index.html"