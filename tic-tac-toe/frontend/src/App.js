import React, { Component } from 'react';
import './App.css';
import HorizontalHashComponent from './HorizontalHashComponent';
import VerticalHashComponent from './VerticalHashComponent';

class App extends Component {
  render() {
    return (
      <div >
        <div className="TicTacToe" >
          Tic Tac Toe
        </div >
        <div className={"hello world"}>
          <HorizontalHashComponent />
          <HorizontalHashComponent />
          <VerticalHashComponent />
          <VerticalHashComponent />
        </div >
      </div >
    );
  }
}

export default App;
