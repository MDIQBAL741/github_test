import { useState, useEffect } from "react";

import "./Reg.css";
import axios from "axios";

import Textbox from "../Components/Textbox";

import Dropdown from "../Components/Dropdown";

import { toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

import ButtonComponent from "../Components/Button";

function Reg() {
  const formData = {
    name: "",

    email: "",

    phone: "",

    currentctc: "",

    expectedctc: "",

    pancard: "",

    currentOrg: "",

    skill: { value: "", description: "Select" },

    candidateType: { value: "", description: "Select" },

    level: { value: "", description: "Select" },

    experience: { value: "", description: "Select" },

    baselocation: { value: "", description: "Select" },

    preferedlocation: { value: "", description: "Select" },

    anyOffer: { value: "", description: "Select" },

    noticePeriod: { value: "", description: "Select" },
  };

  const [name, setName] = useState("");

  const [regForm, setRegForm] = useState(formData);
  const [selectedFile, setSelectedFile] = useState(null);
  const [skill, setSkill] = useState("");
  const handleFileSelect = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const updateForm = (keyName, value) => {
    setRegForm((prevState) => {
      let newState = formData;

      if (keyName === "candidateType" && value.value == 1) {
        newState = { ...prevState, [keyName]: value };
      } else {
        newState = { ...prevState, [keyName]: value };
      }

      return newState;
    });
  };

  const clearForm = () => {
    setRegForm(formData);
    setSkill("");
    setSelectedFile(null);
  };
  const handleForm = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("candidateType", regForm.candidateType.value);
    formData.append("name", regForm.name);
    formData.append("email", regForm.email);
    formData.append("skill", skill);
    formData.append("level", regForm.level.value);
    formData.append("phone", regForm.phone);
    formData.append("experience", regForm.experience.value);
    formData.append("baselocation", regForm.baselocation.value);
    formData.append("preferedloaction", regForm.preferedlocation.value);
    formData.append("pancard", regForm.pancard);
    formData.append("employeeID", regForm.employeeId);
    formData.append("currentctc", regForm.currentctc);
    formData.append("expectedctc", regForm.expectedctc);
    formData.append("currentOrg", regForm.currentOrg);
    formData.append("noticePeriod", regForm.noticePeriod.value);
    formData.append("anyOffer", regForm.anyOffer.value);
    formData.append("resume", selectedFile);
    console.log(formData, "final");
    axios

      .post("http://localhost:8083/addcandidate", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })

      .then((dat) => {
        toast.success("Candidate Register Sucessfully")
      })

      .catch((err) => console.log(err));
      clearForm();
   
  };

  useEffect(() => {
  }, [regForm]);
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
          <h4>Register Candidate</h4>
        </header>

        <div class="formd">
          <div class="names">
            <div class="drop1">
              <Dropdown
                label={"Candidate Type"}
                handleChange={(value) => updateForm("candidateType", value)}
                val={regForm?.candidateType?.value}
                data={[
                  { label: "Internal", value: 1 },

                  { label: "External", value: 2 },
                ]}
              />
            </div>

            <div>
              {" "}
              <Textbox
                label="name"
                placeholder="Enter Name"
                handleChange={(value) => updateForm("name", value)}
                value={regForm.name}
                maxlength={"30"}
                format={"text"}
              />
            </div>

            <div>
              <Textbox
                label="phone"
                placeholder="Enter Phone Number"
                handleChange={(value) => updateForm("phone", value)}
                value={regForm.phone}
                format={"tel"}
                maxlength={"10"}
                type={"num"}
              />
            </div>
          </div>

          <div class="sec1">
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
            <div>
              <Dropdown
                label={"level"}
                handleChange={(value) => updateForm("level", value)}
                val={regForm?.level?.value}
                data={[
                  { label: "1", value: 1 },

                  { label: "2", value: 2 },

                  { label: "3", value: 3 },

                  { label: "4", value: 4 },

                  { label: "5", value: 5 },

                  { label: "6", value: 6 },
                ]}
              />
            </div>

            <div>
              <Dropdown
                label={"experience"}
                handleChange={(value) => updateForm("experience", value)}
                val={regForm?.experience?.value}
                data={[
                  { label: "5 Years", value: "5 Years" },

                  { label: "6 Years", value: "6 Years" },

                  { label: "7 Years", value: "7 Years" },

                  { label: "8 Years", value: "8 Years" },

                  { label: "9 Years", value: "9 Years" },

                  { label: "10+ Years", value: "10+ Years" },
                ]}
              />
            </div>
          </div>

          <div class="sec2">
            <div>
              <Textbox
                label="email"
                placeholder="Enter Email"
                handleChange={(value) => updateForm("email", value)}
                value={regForm.email}
                maxlength={"30"}
                //type={"email"}
              />
            </div>

            <div class="drop1">
              <Dropdown
                label={"base location"}
                handleChange={(value) => updateForm("baselocation", value)}
                val={regForm?.baselocation?.value}
                data={[
                  { label: "Hyderabad", value: "Hyderabad" },

                  { label: "pune", value: "pune" },

                  { label: "Bengaluru", value: "Bengaluru" },

                  { label: "Chennai", value: "Chennai" },

                  { label: "Mumbai", value: "Mumbai" },

                  { label: "Noida", value: "Noida" },
                ]}
              />
            </div>

            <div class="drop1">
              <Dropdown
                label={"preferred location "}
                handleChange={(value) => updateForm("preferedlocation", value)}
                val={regForm?.preferedlocation?.value}
                data={[
                  { label: "Chennai", value: "Chennai" },

                  { label: "Pune", value: "Pune" },
                ]}
              />
            </div>
          </div>

          {regForm?.candidateType?.value == 1 && (
            <div class="names">
              {" "}
              <div>
                <Textbox
                  label="Employee ID"
                  placeholder="Enter Employee Id"
                  handleChange={(value) => updateForm("employeeId", value)}
                  value={regForm.employeeId}
                  maxlength={"7"}
                  type={"num"}
                />
              </div>
              <div class="hidden">
                <Textbox
                  label="Employee ID"
                  placeholder="Enter Employee Id"
                  handleChange={(value) => updateForm("employeeId", value)}
                  value={regForm.employeeId}
                  maxlength={"7"}
                  type={"num"}
                />
              </div>
              <div class="hidden">
                <Textbox
                  label="Employee ID"
                  placeholder="Enter Employee Id"
                  handleChange={(value) => updateForm("employeeId", value)}
                  value={regForm.employeeId}
                  maxlength={"7"}
                  type={"num"}
                />
              </div>
            </div>
          )}

          {regForm?.candidateType?.value == 2 && (
            <div id="hidden">
              <div class="names1">
                <Textbox
                  label="current CTC"
                  placeholder="CTC in LPA"
                  handleChange={(value) => updateForm("currentctc", value)}
                  value={regForm.currentctc}
                  type={"num"}
                  maxlength={4}
                />

                <div>
                  <Textbox
                    label="expected CTC"
                    placeholder="CTC in LPA"
                    handleChange={(value) => updateForm("expectedctc", value)}
                    value={regForm.expectedctc}
                    format={"num"}
                    maxlength={4}
                  />
                </div>

                <div>
                  <Textbox
                    label="current org"
                    placeholder="Org Name"
                    handleChange={(value) => updateForm("currentOrg", value)}
                    value={regForm.currentOrg}
                    format={"text"}
                    type={"alpha"}
                  />
                </div>
              </div>

              <div class="sec3">
                <div>
                  <Textbox
                    label="PAN"
                    placeholder="Enter PAN"
                    handleChange={(value) => updateForm("pancard", value)}
                    value={regForm.pancard}
                    format={"custom"}
                    maxlength={10}
                  />
                </div>

                <div class="d">
                  <Dropdown
                    label={"notice Period"}
                    handleChange={(value) => updateForm("noticePeriod", value)}
                    val={regForm?.noticePeriod?.value}
                    format={"Number"}
                    data={[
                      { label: "immediately", value: "immediately" },

                      { label: "15 days", value: "15 days" },

                      { label: "30 days", value: "30 days" },

                      { label: "60 days", value: "60 days" },
                    ]}
                  />
                </div>

                <div class="d">
                  <Dropdown
                    label={"Any Offer in Hand"}
                    handleChange={(value) => updateForm("anyOffer", value)}
                    val={regForm?.anyOffer?.value}
                    data={[
                      { label: "Yes", value: "Yes" },

                      { label: "No", value: "No" },
                    ]}
                  />
                </div>
              </div>
            </div>
          )}

          <div class="s1">
            <div>
              <label>resume</label>

              <input
                type="File"
                name="resume"
                accept=".pdf,.docx"
                class="file"
                onChange={handleFileSelect}
              />
            </div>
          </div>

          <div class="button">
            <ButtonComponent
              type={"submit"}
              label={"Submit"}
              variant={"light"}
              handleForm={handleForm}
              // disabled={false}
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

export default Reg;
