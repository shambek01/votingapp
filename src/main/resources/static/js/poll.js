function onRadioClick(element){
    const els = element.parentElement.parentElement;

    for(var i = 0; i< els.childNodes.length; i++){
        if(els.childNodes[i].className == "poll-div")
            els.childNodes[i].style.backgroundColor = "#4d648d";
    }
    element.parentElement.style.backgroundColor="lightgreen";
    element.parentElement.style.color="#1e1f26";

}

var counter = 3
function addNew(){
    if(counter<7){
        var form = document.getElementById("poll-form");
        var input = document.createElement("input")
        input.type = "text";
        input.className = "poll-input"
        input.placeholder = "Option " + counter;
        input.name="question"+ counter;
        var elements = document.getElementsByClassName("poll-footer")
        form.insertBefore(input,elements[0])
        counter++;
    }else{
        alert("Sorry, Bitch, Max Question Size is 6");
    }
}