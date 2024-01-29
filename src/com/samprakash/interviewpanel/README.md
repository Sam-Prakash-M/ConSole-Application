# InterviewPanel Application

## Console Application Overview

InterviewPanel is a console application designed for managing job interviews. The target audience for this application is candidates who are seeking employment and need to go through the interview process.

## Features

### 1. Add Candidates

This feature allows the user to add candidates who need to be interviewed. The application collects information such as name, age, and qualification for each candidate.

### 2. Predefined Questions

The application comes with a set of predefined questions for the interview. These questions are used to assess the candidates during the interview process.

### 3. Interview Process

Candidates go through an interview where they answer the predefined questions. A candidate needs to provide correct answers to at least 3 out of the 5 questions to be considered for selection.

### 4. Candidate Selection

Candidates who successfully answer at least 3 questions are selected for further consideration. Their details are displayed as selected candidates.

### 5. Viewing Selected Candidates

The application provides an option to view the list of candidates who have successfully cleared the interview and are selected for further rounds.

### 6. Viewing Waiting Candidates

Candidates who are waiting for their turn to be interviewed can be viewed using this feature. It displays the list of candidates who are yet to go through the interview process.

## Usage

1. Run the application.
2. Add candidates using the provided prompts.
3. Start the interview process to assess candidates.
4. View the list of selected candidates.
5. View the list of candidates waiting for an interview.
6. Exit the application when done.









```
+-------------------------+          +------------------------------+
|      Candidates         |          |      Repository              |
+-------------------------+          +------------------------------+
| - age: int              |          | - listOfQuestions: String[]  |
| - name: String          |          | - solutions: String[]        |
| - qualification: String |          +------------------------------+
+-------------------------+
          |
          |
+-----------------------------------------------------------------------------------+
|                                  BaseView                                         |
+-----------------------------------------------------------------------------------+
| - baseViewModel: BaseViewModel                                                    |
| - scanner: Scanner                                                                |
+-----------------------------------------------------------------------------------+
| + startTheApplication(): void                                                     |
| + addCandidates(): void                                                           |
| + showError(error: String): void                                                  |
| + interviewCurrentCandidate(currCandidate: Candidates, hr: String[], i: int): void|
| + candidateStatus(status: String): void                                           |
| + shoAllCandidates(listOfCandidates: Queue<Candidates>): void                     |
| + shoAllSelectedCandidates(selectedCandidates: List<Candidates>): void            |
| + resultAnnouncement(selectedCandidates: List<Candidates>): void                  |
+-----------------------------------------------------------------------------------+
          |
          |
+---------------------------------------------------------+
|  BaseViewModel                                          |
+---------------------------------------------------------+
| - baseView: BaseView                                    |
| - hr: String[]                                          |
| - listOfCandidates: Queue<Candidates>                   |
| - selectedCandidates: List<Candidates>                  |
+---------------------------------------------------------+
| + interviewProcess(): void                              |
| + isClearedTheRound(currCandidate: Candidates): boolean |
| + resultAnnouncement(): void                            |
| + showAllCandidates(): void                             |
| + showAllSelectedCandidates(): void                     |
| + addCandidate(candidates: Candidates): void            |
+---------------------------------------------------------+
```