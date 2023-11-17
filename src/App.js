import React from "react";
import "./App.css";

import 'bootstrap/dist/css/bootstrap.min.css';

import Header from "./Components/Header";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Panel from "./pages/Panel";
import Reg from "./pages/Reg";
import Sidenav from "./Components/Sidenav";
import Requirement from "./pages/RequirementForms";
import Skills from "./pages/Skills";
import InterviewSch from "./pages/InterviewSch";
// import Inter from "./pages/Inter";
import Status from "./pages/StatusUpdate";
import Report from "./pages/Report";

import { ToastContainer } from "react-toastify";

function App() {
  return (
    <div className="App">
       <ToastContainer theme="dark"/>
      <BrowserRouter>
      <Header/>
      <div style={{display:"flex",msFlexDirection:"row"}}>
       <Sidenav/>
        <Routes>
          <Route path="/Panel"  exact Component={Panel} />
          <Route path="/registration"  Component={Reg} />
          <Route path="/RequirementForms"  Component={Requirement} />
          <Route path="/Skills" Component={Skills}/>
          <Route path="/InterviewSch" Component={InterviewSch}/>
          <Route path="/StatusUpdate" Component={Status}/>
          <Route path="/Report" Component={Report}/>

        </Routes>
        </div>
      </BrowserRouter>

    </div>
  );
}
export default App;
