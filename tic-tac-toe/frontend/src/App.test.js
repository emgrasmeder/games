import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { shallow } from 'enzyme';
import HorizontalHashComponent from "./HorizontalHashComponent";
import VerticalHashComponent from "./VerticalHashComponent";

describe('TicTacToe App', () => {
  it('should render without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<App />, div);
    ReactDOM.unmountComponentAtNode(div);
  });
  it('should render two horizontal HashComponents', () => {
    const wrapper = shallow(<App />);
    expect(wrapper.find(HorizontalHashComponent)).toHaveLength(2)
  });
  it('should render two vertical HashComponents', () => {
    const wrapper = shallow(<App />);
    expect(wrapper.find(VerticalHashComponent)).toHaveLength(2)
  });
});

