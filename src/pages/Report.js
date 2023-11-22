import React, { useState } from "react";
import ButtonComponent from "../Components/Button";
import Textbox from "../Components/Textbox";
import axios from "axios";
import Dropdown from "../Components/Dropdown";
import { Button } from "react-bootstrap";
export default function Report() {
 
  const formData = {
    date1: "",
    date2: "",
  };
  const formData1 ={
    status: "",
  }
  const [rform, setrform] = useState(formData);
  const [masterData, setMasterData] = useState([]);
  const [interviewmaster, setinterviewmaster] = useState([]);
  const [status, setstatus] = useState(false);
  const [interviewStatus, setInterviewStatus] = useState(false);
  const [rform1, setrform1] = useState(formData1);

  const updateForm = (keyName, value) => {
    setrform((prevState) => {
      let newState = formData;
      newState = { ...prevState, [keyName]: value };
      return newState;
    });
  };
  const clearForm = () => {
    setrform(formData);
    setrform1(formData1);
  };

  const handleForm = (e) => {
    e.preventDefault();
    if (status) {
      {
        const formData = {
          date1: rform.date1,
          date2: rform.date2,
        };
        axios
          .get(
            "http://localhost:8083/report/status_between_dates/" +
              rform.date1 +
              "/" +
              rform.date2,
            formData
          )
          .then((repo) => {
            setMasterData(repo.data);
            console.log(rform.date1);
            console.log(rform.date2);
            console.log(repo);
            console.log(repo.data);
          })
          .catch((err) => console.log(err));
        clearForm();
        console.log("button 1 clicked");
      }
    }
    if (interviewStatus) {
      const formData = {
        date1: rform.date1,
        date2: rform.date2,
      };
      axios
        .get(
          "http://localhost:8083/report/interview_schedule_between/" +
            rform.date1 +
            "/" +
            rform.date2,
          formData
        )
        .then((interview) => {
          setinterviewmaster(interview.data);
          console.log(interview.data);
        })
        .catch((err) => console.log(err));
      clearForm();
      console.log("button 2 clicked");
    }
  };

  return (
    <div class="main">
      <form class="form" onSubmit={handleForm}>
        <header class="header">
          <h4>Report Service</h4>
        </header>

        <div class="formd">
          <div class="names">
            <Textbox
              label="From"
              placeholder="Enter Date"
              handleChange={(value) => updateForm("date1", value)}
              value={rform.date1}
              format={"date"}
            />
            <Textbox
              label="To"
              placeholder="Enter Date"
              handleChange={(value) => updateForm("date2", value)}
              value={rform.date2}
              format={"date"}
            />
            <div className="cs">
              <button
                onClick={() => {
                  setstatus(true);
                  setInterviewStatus(false);
                }}
                type={"submit"}
                label={"Check Status"}
                variant={"light"}

                // handleForm={handleForm}
              >
                status check
              </button>
            </div>{" "}
            <div className="cs">
              <button
                onClick={() => {
                  setInterviewStatus(true);
                  setstatus(false);
                }}
                type={"submit"}
                label={"Check Status"}
                variant={"light"}

                // handleForm={handleForm}
              >
                Interview status
              </button>
            </div>
            
          </div>

          <div>
            <form>
              <div className="Table-wrapper">
                {status && (
                  <table className="table">
                    <thead>
                      <tr>
                        <th>Candidate_Id</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Comment</th>
                      </tr>
                    </thead>
                    <tbody>
                      {status &&
                        masterData.map((repo) => (
                          <tr>
                            <td>{repo.candidate_Id}</td>
                            <td>{repo.name}</td>
                            <td>{repo.email}</td>
                            <td>{repo.status}</td>
                            <td>{repo.comment}</td>
                          </tr>
                        ))}
                    </tbody>
                  </table>
                )}
                <div className="Table-wrapper">
                  {interviewStatus && (
                    <table className="table">
                      <thead>
                        <tr>
                          <th>Candidate_Email</th>
                          <th>Skill</th>
                          <th>Level</th>
                          <th>Panel_Email</th>
                          <th>Date</th>
                          <th>Start_Time</th>
                          <th>End_Time</th>
                        </tr>
                      </thead>
                      <tbody>
                        {interviewStatus &&
                          interviewmaster.map((interview) => (
                            <tr>
                              <td>{interview.candiEmail}</td>
                              <td>{interview.skill}</td>
                              <td>{interview.level}</td>
                              <td>{interview.panelEmail}</td>
                              <td>{interview.date}</td>
                              <td>{interview.startTime}</td>
                              <td>{interview.endTime}</td>
                            </tr>
                          ))}
                      </tbody>
                    </table>
                  )}
                </div>
              </div>
            </form>
          </div>
          <div>
            <form></form>
          </div>
        </div>
       
      </form>
    </div>
  );
}
