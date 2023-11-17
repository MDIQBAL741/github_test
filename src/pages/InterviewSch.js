import { useState, useEffect } from "react";
import "./Reg.css";
import axios from "axios";
import Textbox from "../Components/Textbox";
import Dropdown from "../Components/Dropdown";
// import Sidenav from "../Components/Sidenav";
import ButtonComponent from "../Components/Button";
import { toast } from "react-toastify";
function InterviewSch() {
  const formData = {
    name: "",
    email: "",
    level: { value: "", description: "Select" },
    programs: { value: "", description: "Select" },
    panel: { value: "", description: "Select" },
    skills: "",
    date: "",
    startTime: "",
    endTime: "",
    insSlots: "",
    panelEmail:"",
    numberOfSlots: "",
  };

  const [insForm, setInsForm] = useState(formData);
  const [candidate, setCandidate] = useState([]);
  const [candidateemail, setCandidateemail] = useState("");
  const [panels, setpanels] =useState([]);
  const [panelname, setpanelname] =useState("");
  const [panelemail, setpanelemail] =useState("");
  const [numberOfSlots, setnumberOfSlots]= useState("");

  const updateForm = (keyName, value) => {
    setInsForm((prevState) => {
      let newState = formData;
      if (keyName === "programs" && value.value === 1) {
        newState = { ...prevState, [keyName]: value };
      } else {
        newState = { ...prevState, [keyName]: value };
      }
      return newState;
    });
  };
  const clearForm = () => {
    setInsForm(formData);
    setCandidate("");
    setCandidateemail("")
    setpanelname("")
    setpanels([])

  };
  const handleForm = (e) => {
    e.preventDefault();
    const formData ={
      program: insForm.programs.value,
      candidateName: candidate[0].name,
      candiEmail: candidateemail,
      panelName: panelname,
      panelEmail: panels[0].panelEmail,
      startTime: insForm.startTime,
      endTime: insForm.endTime,
      level: insForm.level.value,
      date: insForm.date,
      skill: candidate[0].skill,
      numberOfSlots: numberOfSlots,

    };
    
    axios
      .post("http://localhost:8083/interview/scheduleInterview", formData)
      .then((dat) => {
        console.log(formData);
        toast.success("Interview Schedule Sucessfully")
      })
      .catch((err) => console.log(err));


      clearForm();
  };
  useEffect(() => {
  }, [insForm]);
  
  const [values, setvalues] = useState([]);

  const getcandidate = () => {
    axios
      .get("http://localhost:8083/interview/all")
      .then((candidate) => {
        setvalues(candidate.data);
      })
      .catch((err) => console.log(err));
  };

  const gettingCadndidateValues = (e) => {
    const selectedValue = e.target.value;
    console.log(e.target.value);
    const candidates = [...values];
    const sortedCandidate = candidates.filter(
      (candidate) => candidate.email === selectedValue
    );
    setCandidate(sortedCandidate);
    setCandidateemail(e.target.value);
    
  };
  useEffect(() => {
    getcandidate();
  }, []);

  const [panel, setpanel] = useState([]);
  const getpanel = () => {
    axios
      .get("http://localhost:8083/interview/allpanel")
      .then((panel) => {
        setpanel(panel.data);
      })
      .catch((err) => console.log(err));
  };
  const paneldetails = (e) => {
    const selectpanel = e.target.value;
    const panels = [...panel];
    const sortedpanel = panels.filter(
      (pan) => pan.panelName ==selectpanel
    );
    setpanels(sortedpanel);
    setpanelname(e.target.value);
    setpanelemail(e.target.panelEmail);
    setnumberOfSlots(e.target.numberOfSlots);
    
  };
  useEffect(() => {
    getpanel();
  }, []);
 
  return (
    <div class="main">
      <form class="form" onSubmit={handleForm}>
        <header class="header">
          <h4>InterviewSchedule</h4>
        </header>
        <div class="formd">
          <div class="names">
            <div class="drop">
              <Dropdown
                label={"Programs"}
                handleChange={(value) => updateForm("programs", value)}
                val={insForm?.programs?.value}
                data={[
                  { label: "Qualified Service", value: "Qualified Service" },
                  { label: "DSC-Disputes", value: "DSC-Disputes" },
                  { label: "DSC-Service", value: "DSC-Service" },
                  { label: "DSC-Commercial", value: "DSC-Commercial" },
                  { label: "Optis Modernization", value: "Optis Modernization" },
                ]}
              />
            </div>

            <div>
              <div>
                <label>Candidate Email</label>
                
                <select aria-label="Default select example" class="ddl-style form-select" onChange={gettingCadndidateValues} 
                value={candidateemail}
                >
                  <option>choose email</option>
                  {values.map((opts, i) => (
                    <option>{opts.email}</option>
                  ))}
                </select>
              </div>
            </div>
            <div>
              <Textbox
                label="Candidate Name"
                placeholder="Name"
                handleChange={(value) => updateForm("name", value)}
                value={
                  candidate && candidate.length > 0 ? candidate[0].name : ""
                }
                // disabled={true}
                format={"text"}
                maxlength={"30"}
              />
            </div>
          </div>
          <div class="sec1">
            <div>
              <Textbox
                label="Skills"
                placeholder="Skill"
                handleChange={(value) => updateForm("skills", value)}
                value={
                  candidate && candidate.length > 0 ? candidate[0].skill : ""
                }
                format={"text"}
                maxlength={"50"}
              />
            </div>
            <div>
              <Dropdown
                label="level"
                placeholder="Enter Level"
                handleChange={(value) => updateForm("level", value)}
                val={insForm?.level?.value}
                data={[
                  { label: "L-1", value: "L-1" },
                  { label: "L-2", value: "L-2" },
                  { label: "HR", value: "HR" },
                ]}
              />
            </div>
            <div>
              <Textbox
                label="Date"
                placeholder="Enter Date"
                handleChange={(value) => updateForm("date", value)}
                value={insForm.date}
                format={"date"}
              />
            </div>
          </div>

          <div class="sec2">
            <div>
              <Textbox
                label="Start Time"
                placeholder="Enter Start Time"
                handleChange={(value) => updateForm("startTime", value)}
                value={insForm.startTime}
                format={"time"}
              />
            </div>
            <div>
              <Textbox
                label="End Time"
                placeholder="Enter EndTime"
                handleChange={(value) => updateForm("endTime", value)}
                value={insForm.endTime}
                format={"time"}
              />
            </div>
            <div>
              <label>Panel Name</label>
              <select aria-label="Default select example" class="ddl-style form-select" onChange={paneldetails} value={panelname}>
                <option>Select Panel</option>
                {panel.map((opts, i) => (
                  <option>{opts.panelName}</option>
                ))}
              </select>
            </div>
          </div>
          <div class="sec2">
          <div>
              {" "}
              <Textbox
                label="Panel Email"
                placeholder="Panel Email"
                handleChange={(value) => updateForm("panelEmail", value)}
                value={panels && panels.length > 0 ? panels[0].panelEmail : ""}
                
                
              />
            </div>
                   <div>
              {" "}
              <Textbox
                label="Slot"
                placeholder="No. of Slot"
                handleChange={(value) => updateForm("numberOfSlots", value)}
                value={panels && panels.length > 0 ? panels[0].numberOfSlots : ""}
                format={"num"}
                
              />
            </div>
            
          </div>


          <div class="button">
            <ButtonComponent
            id="liveToastBtn"
              type={"submit"}
              label={"Schedule"}
              variant={"light"}
              handleForm={handleForm}    
              disabled={!candidateemail || !panelname || !insForm.programs.value || !insForm.level.value || !insForm.date || !insForm.startTime || !insForm.endTime}
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

export default InterviewSch;
