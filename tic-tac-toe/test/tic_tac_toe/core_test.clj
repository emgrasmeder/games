(ns tic-tac-toe.core-test
 (:require [clojure.test :refer :all]
  [tic-tac-toe.core :as ttt]))

(deftest base-board-test
 (testing "should start with a 3x3 board of 9 nil spaces"
  (let [expected-board {:00 nil :10 nil :20 nil
                        :01 nil :11 nil :21 nil
                        :02 nil :12 nil :22 nil}]
   (is (= expected-board ttt/base-board)))))

(deftest make-move-test
 (testing "should change space at coordinates to specified value"
  (is (= "O" (:22  (ttt/make-move ttt/base-board "O" 2 2))))
  (is (= "X" (:11  (ttt/make-move ttt/base-board "X" 1 1))))
  (is (= "hello world" (:00  (ttt/make-move ttt/base-board "hello world" 0 0)))))

 (testing "should update board without changing existing moves"
  (let [initial-board (assoc ttt/base-board :00 "X")
        changed-board (ttt/make-move initial-board "O" 2 2)]
   
   (is (= "X" (:00 changed-board)))
   (is (= "O" (:22 changed-board))))))

(deftest get-moves-test  
  (testing "should return coordinates of available moves"
    (let [available-moves (keys ttt/base-board)]
      (is (= available-moves (ttt/get-moves ttt/base-board))))))

(deftest has-diagonal-winner?-test
  (testing "should return true if one player has on a diagonal"
    (let [board-with-descending-line-winner (assoc ttt/base-board :00 "X" :11 "X" :22 "X")
          board-with-ascending-line-winner (assoc ttt/base-board :02 "X" :11 "X" :20 "X")]
      (is (true? (ttt/has-diagonal-winner? board-with-descending-line-winner)))
      (is (true? (ttt/has-diagonal-winner? board-with-ascending-line-winner))))))

(deftest has-horizontal-winner?-test
  (testing "should return true if one player has on a diagonal"
    (let [board1 (assoc ttt/base-board :00 "X" :10 "X" :20 "X")
          board2 (assoc ttt/base-board :01 "X" :11 "X" :21 "X")
          board3 (assoc ttt/base-board :02 "X" :12 "X" :22 "X")]
      (is (true? (ttt/has-horizontal-winner? board1)))
      (is (true? (ttt/has-horizontal-winner? board2)))
      (is (true? (ttt/has-horizontal-winner? board3))))))

(deftest has-vertical-winner?-test
  (testing "should return true if one player has on a diagonal"
    (let [board1 (assoc ttt/base-board
                        :00 "X"
                        :01 "X"
                        :02 "X")
          board2 (assoc ttt/base-board
                        :10 "X"
                        :11 "X"
                        :21 "X")
          board3 (assoc ttt/base-board
                        :20 "X"
                        :21 "X"
                        :22 "X")]
      (is (true? (ttt/has-vertical-winner? board1)))
      (is (true? (ttt/has-vertical-winner? board2)))
      (is (true? (ttt/has-vertical-winner? board3))))))

