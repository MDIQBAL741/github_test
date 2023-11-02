import React from "react";
import { Link } from "react-router-dom";
function Sidenav() {
  return (
    <aside>
      <div>
        <nav>
          <div class="navdiv">
            <div class="href">
              <a href=" " class="href1">
             <Link to="/registration" class="href1" >Register Candidate</Link>
              </a>
            </div>
            <div class="href">
              <Link to="/Skills" class="href1">
                Skill Services
              </Link>
            </div>
            <div class="href">
              <a href="" class="href1">
              <Link to="/Panel" class="href1">  Panel Services</Link>
              </a>
            </div>
            
            <div class="href">
              <a href="" class="href1">
              Add Panel Time Slot
              </a>
            </div>
            <div class="href">
              <Link to="/InterviewSch" class="href1">
              Interview Schedule
              </Link>
            </div>
            <div class="href">
              <Link to="/StatusUpdate" class="href1">
              Status Update
              </Link>
            </div>
            <div class="href">
              <a href="" class="href1">
              Report Service
              </a>
            </div>
            <div class="href">
              <a href="" class="href1">
              <Link to="/RequirementForms" class="href1">Requirement Form</Link>
              </a>
              </div>
          </div>
        </nav>
      </div>
    </aside>
  );
}
export default Sidenav;
