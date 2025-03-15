// import logo from './logo.svg';
// import './App.css';
import { useEffect } from 'react';
import './styles/tmpLogin.scss';


const loginEvent = () => {
  const $loginBtn = document.getElementById("loginBtn");
  $loginBtn.addEventListener("click", () => {
    console.log("로그인 테스트");

    // TODO: 로그인 로직. spring security 의 loginProcessingUrl 호출.

  });
}

function App() {
  return (
    // 그러고 보니, app.js 가 최초 컨텍스트 패스의 메인 화면인가...?
    <div className="tmp_container">
      <div className="login_container">
        <form className="login_form">
          <h2>LOGIN_TMP</h2>
          <div>
            <label htmlFor="userId">ID</label>
            <input type="text" name="userId" id="userId" maxLength={20}/>
          </div>

          <div>
            <label htmlFor="userPw">비밀번호</label>
            <input type="password" name="userPw" id="userPw" maxLength={20} />
          </div>

          <div>
            <label htmlFor="autoLogin">자동 로그인</label>
            <input type="checkbox" id="autoLogin" name="autoLogin" />
          </div>

          <div className="btn_box">
            <button type="button" id="loginBtn" onClick={loginEvent}>로그인</button>
          </div>
        </form>
      </div>
    </div>
  );
  // return (
  //   <div className="App">
  //     <header className="App-header">
  //       <img src={logo} className="App-logo" alt="logo" />
  //       <p>
  //         Edit <code>src/App.js</code> and save to reload.
  //       </p>
  //       <a
  //         className="App-link"
  //         href="https://reactjs.org"
  //         target="_blank"
  //         rel="noopener noreferrer"
  //       >
  //         Learn React
  //         asdf
  //       </a>
  //     </header>
  //   </div>
  // );
}

export default App;
