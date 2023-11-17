import { useState, useEffect } from "react";

import "./Reg.css";

import Textbox from "../Components/Textbox";

import Dropdown from "../Components/Dropdown";

// import Sidenav from "../Components/Sidenav";

import ButtonComponent from "../Components/Button";
import { toast } from "react-toastify";

import axios from "axios";

function Panel() {
  const formData = {
    panelName: "",

    panelId: "",

    panelEmail: "",

    timeSlot: "",

    date: "",

    numberOfSlots: "",

    startTime: "",

    endTime: "",

    panelType: "",

    skills: "",
  };

  const [name, setName] = useState("");

  const [panelForm, setPanelForm] = useState({ FormData });
  const [skill, setSkill] = useState("");

  const updateForm = (keyName, value) => {
    setPanelForm((prevState) => {
      const newState = { ...prevState, [keyName]: value };

      return newState;
    });
  };

  const clearForm = () => {
    setPanelForm(formData);
  };

  const handleForm = (e) => {
    e.preventDefault();
    const formData = {
      panelName: panelForm.panelName,
      panelEmail: panelForm.panelEmail,
      skill: skill,
      date: panelForm.date,
      numberOfSlots: panelForm.numberOfSlots,
      startTime: panelForm.startTime,
      endTime: panelForm.endTime,
    };

    axios

      .post("http://localhost:8083/addpanel", formData)

      .then((dat) => {
        toast.success("Panel Register Sucessfully");
      })

      .catch((err) => console.log(err, formData));
    clearForm();
  };

  useEffect(() => {
    console.log("useEffect", panelForm);
  }, [panelForm]);
  const [values, setvalues] = useState([]);
  const getskills = () => {
    axios
      .get("http://localhost:8083/getallskills")
      .then((skill) => {
        setvalues(skill.data);
      })
      .catch((err) => console.log(err));
  };

  const skillvalue = (e) => {
    const selectedvalue = e.target.value;
    console.log(selectedvalue, "skill");
    setSkill(e.target.value);
  };
  useEffect(() => {
    getskills();
  }, []);
  return (
    <div class="main">
      <form class="form" onSubmit={handleForm}>
        <header class="header">
          <h4>Panel Services</h4>
        </header>

        <div class="formd">
          <div class="names">
            <div class="d">
              <Dropdown
                label={"Panel Type"}
                handleChange={(value) => updateForm("panelType", value)}
                val={panelForm?.panelType}
                data={[
                  { label: "Internal", value: 1 },

                  { label: "External", value: 2 },
                ]}
              />
            </div>

            <div>
              <Textbox
                label="Panel Name"
                placeholder="Enter Panel Name"
                handleChange={(value) => updateForm("panelName", value)}
                value={panelForm.panelName}
                type={"alpha"}
                maxlength={"30"}
              />
            </div>

            <Textbox
              label="Panel-ID"
              placeholder="Enter Panel Id"
              handleChange={(value) => updateForm("panelId", value)}
              value={panelForm.panelId}
              format={"text"}
              maxlength={"10"}
              pattern={"^[0-9]{2}-[0-9]{3}-[0-9]{4}"}
            />
          </div>

          <div class="sec1">
            <div>
              <Textbox
                label="Panel Email"
                placeholder="Enter Panel Email"
                handleChange={(value) => updateForm("panelEmail", value)}
                value={panelForm.panelEmail}
                format={"email"}
                maxlength={"30"}
                pattern={""}
              />
            </div>

            <div class="d">
              <div>
                <label>Skill</label>
                <select
                  aria-label="Default select example"
                  class="ddl-style form-select"
                  onChange={skillvalue}
                >
                  <option>select skill</option>

                  {values.map((opts, i) => (
                    <option>{opts.skill}</option>
                  ))}
                </select>
              </div>
            </div>

            <div>
              <Textbox
                label="Date"
                placeholder="Enter Date"
                handleChange={(value) => updateForm("date", value)}
                value={panelForm.date}
                format={"date"}
                maxlength={""}
                pattern={""}
              />
            </div>
          </div>

          <div class="sec2">
            <div>
              <Textbox
                label="No.Of Slots"
                placeholder="Enter No. of slots"
                handleChange={(value) => updateForm("numberOfSlots", value)}
                value={panelForm.numberOfSlots}
                type={"num"}
                maxlength={"2"}
                pattern={""}
              />
            </div>

            <div>
              <Textbox
                label="Start Time"
                placeholder="Enter Start Time"
                handleChange={(value) => updateForm("startTime", value)}
                value={panelForm.startTime}
                format={"time"}
                maxlength={"10"}
                pattern={""}
              />
            </div>

            <div>
              <Textbox
                label="End Time"
                placeholder="Enter End Time"
                handleChange={(value) => updateForm("endTime", value)}
                value={panelForm.endTime}
                format={"time"}
                maxlength={4}
              />
            </div>
          </div>

          <div class="button">
            <ButtonComponent
              type={"submit"}
              label={"Submit"}
              variant={"light"}
              onClick={handleForm}

              disabled={!panelForm.panelName || !panelForm.panelEmail || !skill || !panelForm.date || !panelForm.numberOfSlots || !panelForm.startTime || !panelForm.endTime}
            />

            <ButtonComponent
              type={"reset"}
              variant={"light"}
              label={"clear"}
              handleClick={clearForm}
            />
          </div>
        </div>
      </form>
    </div>
  );
}

export default Panel;
