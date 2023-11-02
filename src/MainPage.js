import React from "react";
import  "./Main.css"
import { Link } from "react-router-dom";
function MainPage() {
  return (
    <div >
    <aside class="aside">
      <div class="id2">
        <nav>
          <div class="id3">
            <div class="href">
              <Link to="./pages/Reg.js">Registration page</Link>
            </div>
            <div class="href">
              <Link to="./pages/Skills.js">Skill Services</Link>
            </div>
            <div class="href">
              <Link to="./pages/Panel.js">Panel page</Link>
            </div>
            <div class="href">
               <Link to="./pages/AddPanel.js">Add a panel Slot</Link>
            </div>
            <div class="href">
               <Link to="./pages/IntervieSch.js">Interview Schedule</Link>
            </div>
            <div class="href">
               <Link to="./pages/Statusup.js">Status Update</Link>
            </div>
            <div class="href">
               <Link to="./pages/Report.js">ReportService</Link>
            </div>
          </div>
        </nav>
      </div>
      </aside>
      <div>
       <div class="id1">
      <p>
        <h1>Requirement Process</h1>
      </p>
     </div>
     </div>
    </div> 
  );
}
export default MainPage;
