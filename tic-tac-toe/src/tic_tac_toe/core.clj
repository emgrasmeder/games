(ns tic-tac-toe.core
  (:gen-class))

(defn base-board []
  [[nil nil nil]
   [nil nil nil]
   [nil nil nil]])

(defn make-move [old marker "X" 2 2])
