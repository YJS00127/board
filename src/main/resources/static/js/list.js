let boardList = [];
let pageInfo = {};
let pageNumberButtons;
let page;
let keyword;

// 데이터 로딩 후 출력
async function LoadData(){
    searchKeyword()
    const params = new URLSearchParams(location.search);

    page = params.get('page') ?? 0;
    keyword = params.get('keyword') ?? '';


    await fetch(`/api/board?keyword=${keyword}&page=${page}`)
        .then(response => response.json())
        .then(data => {
            boardList = data.pages.content;
            pageInfo = data.pages;

            BoardListOutPut()
            Pagination(pageInfo.number)
        })

}

// 게시글 검색
function searchKeyword() {
    document.getElementById("search-input").addEventListener('keyup',
        function(e){
        if(e.key === 'Enter'){
            const keyword = document.getElementById("search-input").value;
            if(keyword !== '' && keyword !== null) {
                location.href = `/board?keyword=${keyword}`;
            }
        }
    })
}

// 게시글 출력
function BoardListOutPut() {
    const tbody = document.querySelector("tbody");
    let boardNumber = pageInfo.number*10;
    tbody.innerHTML = ``;
    boardList.forEach(data => {
        const tr = document.createElement('tr');
        boardNumber += 1;
        tr.innerHTML =
            `<td class="boardNum">${boardNumber}</td>` +
            `<td class="boardTitle">
                <a href="/board/detail/${data.ID}">${data.TITLE}</a>
            </td>` +
            `<td class="boardWriter">${data.WRITER}</td>` +
            `<td class="created-day">${data.CREATED_AT.slice(0,10)}</td>` +
            `<td class="created-time">${data.CREATED_AT.slice(11,16)}</td>`;
        document.getElementById("boardListOutPut").append(tr);
    })
}

// 페이지네이션 (페이지 생성, 클릭 시 이동 이벤트)
function Pagination(currentPage) {

    // 페이지 생성
    const setPageButtons = () => {
        const totalPages = pageInfo.totalPages;

        const numberButtonWrapper = document.querySelector('.number-button-wrapper');
        numberButtonWrapper.innerHTML = '';

        for (let i = 1; i <= totalPages; i++) {
            numberButtonWrapper.innerHTML += `<span class="number-button">${i}</span>`;
        }
    }

    // 페이지 번호 클릭 이벤트
    const PageButtonsEvent = () => {
        pageNumberButtons = document.querySelectorAll('.number-button');

        const params = new URLSearchParams();

        pageNumberButtons.forEach((numberButton) => {
            numberButton.addEventListener('click', (e) => {
                const page = e.target.innerHTML;
                if(keyword !== '' && keyword !== null) {
                    location.href = `/board?keyword=${keyword}&page=${page - 1}`;
                } else{
                    location.href = `/board?page=${page - 1}`;
                }
            })
        })
    }

    // 이전, 다음 클릭 이벤트
    const prevNextButton = () => {
        const prev = document.querySelector('.prev-button');
        const next = document.querySelector('.next-button');

        prev.addEventListener('click', (e) => {
            if(currentPage === 0){
                prev.classList.contains('disabled');
            } else{
                location.href = `/board?page=${currentPage - 1}`;
            }
        })

        next.addEventListener('click', (e) => {
            if(currentPage === (pageInfo.totalPages-1)){
                next.classList.contains('disabled');
            } else{
                location.href = `/board?page=${currentPage + 1}`;
            }
        })
    }

    setPageButtons();
    PageButtonsEvent();
    prevNextButton();

    pageNumberButtons.forEach((numberButton) => {
        if (numberButton.classList.contains('selected')){
            numberButton.classList.remove('selected');
        }
    })

    pageNumberButtons[currentPage].classList.add('selected');

}

LoadData();