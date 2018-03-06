(ns tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.core :as ttt]))

(deftest base-board-test
  (testing "should start with a 3x3 board of 9 nil spaces"
    (let [expected-board [[nil nil nil]
                          [nil nil nil]
                          [nil nil nil]]]
      (is (= expected-board (ttt/base-board))))))

(deftest make-move-test
  (testing "should return new board with symbol at coordinates"
    (let [initial-board (ttt/base-board)
          expected-new-board [[nil nil nil]
                              [nil nil nil]
                              [nil nil "O"]]]
      (is (= expected-new-board (ttt/make-move (ttt/base-board) "O" 2 2))))))

