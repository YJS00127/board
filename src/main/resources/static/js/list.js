let boardList = []
let pageContent = {}

async function LoadData(keyword){
    const page = new URLSearchParams(location.search).get('page') ?? 0

    await fetch(`/api/board?keyword=${keyword}&page=${page}`)
        .then(response => response.json())
        .then(data => {
            boardList = data.pages.content
            pageContent = data.pages

            BoardListOutPut()
            Pagination()
        })
}

function GetKeyword() {
    document.getElementById("search-input").addEventListener('keyup',
        function(e){
        if(e.key === 'Enter'){
            const keyword = document.getElementById("search-input").value
            LoadData(keyword)
        }
    })
}


function BoardListOutPut() {
    const tbody = document.querySelector("tbody")
    let boardNum = pageContent.pageable.pageNumber*10;
    tbody.innerHTML = ``
    boardList.forEach(data => {
        const tr = document.createElement('tr')
        boardNum += 1;
        tr.innerHTML =
            `<td class="boardNum">${boardNum}</td>` +
            `<td class="boardTitle">
                <a href="/board/detail/${data.ID}">${data.TITLE}</a>
            </td>` +
            `<td class="boardWriter">${data.WRITER}</td>` +
            `<td class="created-day">${data.CREATED_AT.slice(0,10)}</td>` +
            `<td class="created-time">${data.CREATED_AT.slice(11,16)}</td>`;
        document.getElementById("boardListOutPut").append(tr)
    })
}

function Pagination(){
    const totalPages = pageContent.totalPages

    const numberButtonWrapper = document.querySelector(`.number-button-wrapper`)

    const setPageButtons = () => {
        numberButtonWrapper.innerHTML = ''

        for(let i=1; i<= totalPages; i++){
            numberButtonWrapper.innerHTML += `<span class="number-button">${i}</span>`
        }
        
    }

    setPageButtons();
}

// 클릭 한 번호 찾아서 url에 올리기


LoadData('')