(ns tic-tac-toe.core-test
 (:require [clojure.test :refer :all]
  [tic-tac-toe.core :as ttt]))

(deftest base-board-test
  (testing "should start with a 3x3 board of 9 nil spaces"
    (let [expected-board [nil nil nil
                          nil nil nil
                          nil nil nil]]
      (is (= expected-board ttt/base-board)))))

(deftest make-move-test

  (testing "should change space at coordinate to specified value"
    (is (= "O" (nth  (ttt/make-move ttt/base-board "O" 9) (- 9 1))))
    (is (= "X" (nth  (ttt/make-move ttt/base-board "X" 1) (- 1 1))))
    (is (= "hello world" (nth  (ttt/make-move ttt/base-board "hello world" 5) (- 5 1))))))

(deftest get-moves-test  
  (testing "should return coordinates of available moves"
    (let [available-moves [1 2 3
                           4 5 6
                           7 8 9]]
      (is (= available-moves (ttt/get-moves ttt/base-board))))))

(deftest has-diagonal-winner?-test
  (testing "should return true if one player has on a diagonal"
    (let [board-with-descending-line-winner (assoc ttt/base-board 1 "X" 5 "X" 9 "X")
          board-with-ascending-line-winner (assoc ttt/base-board 3 "X" 5 "X" 7 "X")]
      (is (true? (ttt/has-diagonal-winner? board-with-descending-line-winner)))
      (is (true? (ttt/has-diagonal-winner? board-with-ascending-line-winner)))
      (is (false? (ttt/has-diagonal-winner? ttt/base-board))))))

(deftest has-horizontal-winner?-test
  (testing "should return true if one player has on a diagonal"
    (let [board1 (assoc ttt/base-board 1 "X" 2 "X" 3 "X")
          board2 (assoc ttt/base-board 4 "X" 5 "X" 6 "X")
          board3 (assoc ttt/base-board 7 "X" 8 "X" 9 "X")]
      (is (true? (ttt/has-horizontal-winner? board1)))
      (is (true? (ttt/has-horizontal-winner? board2)))
      (is (true? (ttt/has-horizontal-winner? board3))))))

(deftest has-vertical-winner?-test
  (testing "should return true if one player has on a diagonal"
    (let [board1 (assoc ttt/base-board
                        1 "X"
                        4 "X"
                        7 "X")
          board2 (assoc ttt/base-board
                        2 "X"
                        5 "X"
                        8 "X")
          board3 (assoc ttt/base-board
                        3 "X"
                        7 "X"
                        9 "X")]
      (is (true? (ttt/has-vertical-winner? board1)))
      (is (true? (ttt/has-vertical-winner? board2)))
      (is (true? (ttt/has-vertical-winner? board3))))))

