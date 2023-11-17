import { useState, useEffect } from "react";

 

import "./Reg.css";

import Textbox from "../Components/Textbox";

import Dropdown from "../Components/Dropdown";

import Sidenav from "../Components/Sidenav";

import ButtonComponent from "../Components/Button";
import axios from "axios";
import { toast } from "react-toastify";

function Requirement() {

  const formData = {

    accountName: "",

    projectName: "",

    managerName: "",

    totalPosition: "",

    skill: { value: "", description: "Select" },

    projectLocation: { value: "", description: "Select" },

  };

  const [name, setName] = useState("");

  const [requirementForm, setRequirementForm] = useState(formData);
  const [skill, setSkill] = useState("");

  const updateForm = (keyName, value) => {

    setRequirementForm((prevState) => {

      const newState = { ...prevState, [keyName]: value };

      return newState;

    });

  };

  const clearForm = () => {

    setRequirementForm(formData);

  };
  const handleForm =(e) =>{
    e.preventDefault();
    const formData ={
        accountName: requirementForm.accountName,
        projectName: requirementForm.projectName,
        managerName: requirementForm.managerName,
        totalPosition: requirementForm.totalPosition,
        projectLocation: requirementForm.projectLocation.value,
        skill: requirementForm.skill.value,
    };
    axios.post("http://localhost:8083/requirement/add",formData)
    .then((dat) => {
      toast.success("Requirement Register sucessfully")
    })
    .catch((err) => console.log(err));
    clearForm();
  }

  useEffect(() => {

  }, [requirementForm]);
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

      {/* <aside>

        <Sidenav />

      </aside> */}

      <form class="form" onSubmit={handleForm}>

        <header class="header">

          <h4>Requirement Form</h4>

        </header>

        <div class="formd">

          <div class="names">

            <Textbox

              label="Requirement ID"

              placeholder="Enter Name"

              handleChange={(value) => updateForm("reqId", value)}

              value={requirementForm.reqId}

              format={"text"}

              maxlength={"30"}

            />

            <div>

              <Textbox

                label="Account Name"

                placeholder="Enter Account Name"

                handleChange={(value) => updateForm("accountName", value)}

                value={requirementForm.accountName}

                format={"text"}

                maxlength={"30"}

              />

            </div>

            <div>

              <Textbox

                label="Project Name"

                placeholder="Enter Project Name"

                handleChange={(value) => updateForm("projectName", value)}

                value={requirementForm.projectName}

                format={"text"}
              />

            </div>

          </div>

          <div class="sec1">

            <div>

              <Textbox

                label="Manager Name"

                placeholder="Enter Manager Name "

                handleChange={(value) => updateForm("managerName", value)}

                value={requirementForm.managerName}

                format={"text"}

                maxlength={10}

              />

            </div>

            <div>

              <Textbox

                label="Total position"

                placeholder="Enter Total postions"

                handleChange={(value) => updateForm("totalPosition", value)}

                value={requirementForm.totalPosition}

                format={"text"}

                maxlength={10}

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

          </div>

 

          <div class="sec3">

            <div class="d">

              <Dropdown

                label={"Project Location"}

                handleChange={(value) => updateForm("projectLocation", value)}

                val={requirementForm?.projectLocation?.value}

                data={[

                  { label: "Chennai", value: "Chennai" },

                  { label: "Pune", value: "Pune" },

                ]}

              />

            </div>

          </div>

          <div class="button">

            <ButtonComponent

              type={"submit"}

              label={"Submit"}

              variant={"light"}
              

              disabled={!requirementForm.accountName || !requirementForm.managerName || !requirementForm.projectName || !requirementForm.projectLocation.value || !requirementForm.totalPosition ||!skill}

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

export default Requirement;