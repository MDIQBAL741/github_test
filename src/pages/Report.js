import React, { useState } from "react";
import ButtonComponent from "../Components/Button";
import Textbox from "../Components/Textbox";
import axios from "axios";
import { Button } from "react-bootstrap";
export default function Report() {
  const formData = {
    date1: "",
    date2: "",
  };
  const [rform, setrform] = useState(formData);
  const [masterData, setMasterData] = useState([]);
  const [interviewmaster, setinterviewmaster] =useState([]);

  const updateForm = (keyName, value) => {
    setrform((prevState) => {
      let newState = formData;
      newState = { ...prevState, [keyName]: value };
      return newState;
    });
  };
  const clearForm = () => {
    setrform(formData);
  };

  const handleForm = (e) => {
    e.preventDefault();

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
  };
  const handleForm1 = (e) => {
    e.preventDefault();

    const formData = {
      date1: rform.date1,
      date2: rform.date2,
    };
    axios
    .get("http://localhost:8083/report/interview_schedule_between/"+rform.date1 +"/" +rform.date2, formData)
    .then((interview)=>{
      setinterviewmaster(interview.data);
    })
    .catch((err)=> console.log(err));
    clearForm();
  };
  return (
    <div class="main">
      <form class="form" onSubmit={(handleForm)}>
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
              <ButtonComponent
                type={"submit"}
                label={"Check Status"}
                variant={"light"}
                handleForm={handleForm}
              />
            </div>{" "}
            {/* <div className="cs">
              <ButtonComponent
                type={"submit"}
                label={"Interview Status"}
                variant={"light"}
                handleForm={handleForm1}
              />
            </div> */}
          </div>

          <div>
            <form>
              <div className="Table-wrapper">
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
                    {masterData.map((repo) => {
                      return (
                        <tr>
                          <td>{repo.candidate_Id}</td>
                          <td>{repo.name}</td>
                          <td>{repo.email}</td>
                          <td>{repo.status}</td>
                          <td>{repo.comment}</td>
                        </tr>
                      );
                    })}
                  </tbody>
                </table>
              </div>
            </form>
          </div>
        </div>
      </form>
    </div>
  );
}
